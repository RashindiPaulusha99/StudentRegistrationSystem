package dto;

public class LoginDTO {
        private String userId;
        private String userName;
        private String password;
        private String name;

        public LoginDTO() {
        }

        public LoginDTO(String userId, String userName, String password, String name) {
            this.setUserId(userId);
            this.setUserName(userName);
            this.setPassword(password);
            this.setName(name);
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Login{" +
                    "userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
}
