package cn.edu.whut.sept.zuul.exception;

public class ContactNotFoundException extends BusinessException {
    //private final int wrongId;
    public ContactNotFoundException() {
        //this.wrongId=wrongId;
        super(404, "指定联系人不存在");
        //this.wrongId=-1;
    }

    public ContactNotFoundException(long wrongId) {
        super(404, String.format("id为 %d 的联系人不存在", wrongId));
    }

}
