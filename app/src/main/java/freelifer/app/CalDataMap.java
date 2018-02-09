package freelifer.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author kzhu on 2018/2/9.
 */
public class CalDataMap {

    public CalData[] calDatas;

    public static class CalData {
        public int type;
        public int[] location;
    }

    public static CalDataMap create(String data) {
        try {
            JSONArray array = new JSONArray(data);
            if (array != null && array.length() > 0) {
                int len = array.length();
                CalData[] calDatas = new CalData[len];
                for (int i = 0; i < len; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    calDatas[i] = new CalData();
                    calDatas[i].type = jsonObject.optInt("type");
                    JSONArray location = jsonObject.optJSONArray("location");
                    if (location != null && location.length() > 0) {
                        int locationLen = location.length();
                        calDatas[i].location = new int[locationLen];
                        for (int j = 0; j < locationLen; j++) {
                            calDatas[i].location[j] = location.optInt(j);
                        }
                    }
                }

                CalDataMap calDataMap = new CalDataMap();
                calDataMap.calDatas = calDatas;
                return calDataMap;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
