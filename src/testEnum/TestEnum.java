package testEnum;

public enum TestEnum implements MethodWork {
    HELLO() {

        @Override
        public int work() {
            return -1;
        }
    },

    SHECK_BUTTON_HELLO() {
        @Override
        public int work() {
            return -1;
        }
    };


    static{
        TestEnum [] arrayEnums = TestEnum.values();
        for (int i = 0; i < arrayEnums.length; i++) {
            arrayEnums[i].status_id = i;
        }
    }

    public static void setValues(String [] arrayMsgs )
    {
        TestEnum [] arrayEnum = TestEnum.values();
        if( arrayEnum != null )
        {
            for (int i = 0; i < arrayEnum.length ; i++ )
            {
                arrayEnum[i].setMsg( arrayMsgs[i] );
            }
        }
    }

    private String msg;
    private int status_id;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgByStatus()
    {
        return getMsgByStatus();
    }

    public  String getMsg() {
        return msg;
    }
}
