package kdh.redisstudy.common.redis;

import com.google.gson.Gson;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisCommon {

    private final RedisTemplate<String, String> template;
    private final Gson gson;

    @Value("${spring.data.redis.default-time}")
    private Duration defaultExpireTime;

    public <T> T getData(String key, Class<T> clazz) {
        String jsonValue = template.opsForValue().get(key);
        if (jsonValue == null) {
            return null;
        }

        return gson.fromJson(jsonValue, clazz);
    }

    public <T> void setData(String key, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForValue().set(key, jsonValue);
        template.expire(key, defaultExpireTime);
    }

    public <T> void multiSetData(Map<String, T> dataMap) {
        Map<String, String> jsonMap = new HashMap<>();

        for (Map.Entry<String, T> entry : dataMap.entrySet()) {
            jsonMap.put(entry.getKey(), gson.toJson(entry.getValue()));
        }

        template.opsForValue().multiSet(jsonMap);
    }

    public <T> void addToSortedSet(String key, T value, Float score) {
        String jsonValue = gson.toJson(value);
        template.opsForZSet().add(key, jsonValue, score);
    }

    public <T> Set<T> rangeByScore(String key, Float minScore, Float maxScore, Class<T> clazz) {
        Set<String> jsonValueSet = template.opsForZSet().rangeByScore(key, minScore, maxScore);
        Set<T> resultSet = new HashSet<>();
        if (jsonValueSet != null) {
            for (String jsonValue : jsonValueSet) {
                T v = gson.fromJson(jsonValue, clazz);
                resultSet.add(v);
            }
        }
        return resultSet;
    }

    public <T> Set<T> getTopNFromSortedSet(String key, int n, Class<T> clazz) {
        Set<String> jsonValueSet = template.opsForZSet().reverseRange(key, 0, n - 1);
        Set<T> resultSet = new HashSet<>();
        if (jsonValueSet != null) {
            for (String jsonValue : jsonValueSet) {
                T v = gson.fromJson(jsonValue, clazz);
                resultSet.add(v);
            }
        }
        return resultSet;
    }

    public <T> void addToListLeft(String key, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForList().leftPush(key, jsonValue);
    }

    public <T> void addToListRight(String key, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForList().rightPush(key, jsonValue);
    }

    public <T> List<T> getAllList(String key, Class<T> clazz) {
        List<String> jsonValueList = template.opsForList().range(key, 0, -1);
        List<T> resultSet = new ArrayList<>();
        if (jsonValueList != null) {
            for (String jsonValue : jsonValueList) {
                T value = gson.fromJson(jsonValue, clazz);
                resultSet.add(value);
            }
        }

        return resultSet;
    }

    public <T> void removeFromList(String key, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForList().remove(key, 1, jsonValue);
    }

    public <T> void putInHash(String key, String hashKey, T value) {
        String jsonValue = gson.toJson(value);
        template.opsForHash().put(key, hashKey, jsonValue);
    }

    public <T> T getFromHash(String key, String hashKey, Class<T> clazz) {
        Object result = template.opsForHash().get(key, hashKey);
        if (result != null) {
            return clazz.cast(result);
        }
        return null;
    }

    public void removeFromHash(String key, String hashKey) {
        template.opsForHash().delete(key, hashKey);
    }
}
