package kdh.redisstudy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kdh.redisstudy.common.redis.RedisCommon;
import kdh.redisstudy.domain.string.model.StringModel;
import kdh.redisstudy.domain.string.model.request.MultiStringRequest;
import kdh.redisstudy.domain.string.model.request.StringRequest;
import kdh.redisstudy.domain.string.model.response.StringResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StringService {

    private final RedisCommon redis;

    public void setString(StringRequest request) {
        String key = request.baseRequest().Key();
        StringModel newModel = new StringModel(key, request.Name());

        redis.setData(key, newModel);
    }

    public StringResponse getString(String key) {
        StringModel result = redis.getData(key, StringModel.class);

        List<StringModel> response = new ArrayList<>();
        if (result != null) {
            response.add(result);
        }
        return new StringResponse(response);
    }

    public void multiString(MultiStringRequest request) {
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < request.Names().length; i++) {
            String name = request.Names()[i];
            String key = request.baseRequest().Key() + (i + 1);
            StringModel newModel = new StringModel(key, name);
            dataMap.put(key, newModel);
        }
        redis.multiSetData(dataMap);
    }
}
