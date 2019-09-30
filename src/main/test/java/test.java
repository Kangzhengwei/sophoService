import com.sopho.entity.BaseResult;
import com.sopho.entity.User;
import com.sopho.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class test {

    public static void main(String args[]) {
        User user = new User("12345678901", "12345678901", "123456", "", "王炸快捷键", "苏州", "男", "2019.4.22");
       /* String str = GsonUtils.getInstance().toJson(user);
        System.out.println(str);*/

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        BaseResult result = new BaseResult(0, "获取成功", null);
        String str = GsonUtils.getInstance().toJson(result);
        System.out.println(str);
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        list.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {

            }
        });
    }
}
