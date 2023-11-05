public class ControlPanel {
    private String password;
    private int passwordLength;
    private int incorrectAttempts;
    private boolean isLocked;
    private long lockStartTime;

    public ControlPanel(String password) {
        this.password = password;
        this.passwordLength = 4;
        this.incorrectAttempts = 0;
        this.isLocked = false;
        this.lockStartTime = 0;
    }

    public void processInput(char key) {
        if (isLocked) {
            if (isLockTimeExpired()) {
                isLocked = false;
            }
            return;
        }

        switch (getState()) {
            case "ReadingInput":
                handleReadingInputState(key);
                break;
            case "PasswordVerification":
                handlePasswordVerificationState(key);
                break;
            case "SystemFunctionSelection":
                handleSystemFunctionSelectionState(key);
                break;
            default:
                // 处理其他状态（如果有）
                break;
        }
    }

    private void handleReadingInputState(char key) {
        // 处理读取键入字符状态
        if (key == '*') {
            incorrectAttempts = 0;
            setState("PasswordVerification");
        }
    }

    private void handlePasswordVerificationState(char key) {
        // 处理验证密码状态
        if (key >= '0' && key <= '9') {
            password += key;
            if (password.length() == passwordLength) {
                if (password.equals(getSavedPassword())) {
                    System.out.println("密码正确！");
                    setState("SystemFunctionSelection");
                } else {
                    System.out.println("密码错误！");
                    incorrectAttempts++;
                    if (incorrectAttempts >= 3) {
                        lockControlPanel();
                    }
                    password = "";
                }
            }
        }
    }

    private void handleSystemFunctionSelectionState(char key) {
        // 处理系统功能选择状态
        // 根据不同的按键执行相应的功能，如开启门锁、报警等
    }

    private String getState() {
        // 获取当前控制面板的状态
        // 可以使用枚举类型或字符串表示不同的状态值
        return null;
    }

    private void setState(String state) {
        // 设置控制面板的状态
    }

    private String getSavedPassword() {
        // 获取保存的密码（从存储中获取）
        return null;
    }

    private boolean isLockTimeExpired() {
        // 判断锁定时间是否已过120秒
        return false;
    }

    private void lockControlPanel() {
        System.out.println("输入密码错误超过3次，锁定控制面板");
        isLocked = true;
        lockStartTime = System.currentTimeMillis();
    }
}
