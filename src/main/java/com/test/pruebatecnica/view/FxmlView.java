package com.test.pruebatecnica.view;

import java.util.ResourceBundle;

public enum FxmlView {

    REGISTER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("register.title");
        }

        @Override
        public String getFxmlFile() {
            return "/register.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/login.fxml";
        }
    },   INICIO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("home.title");
        }

        @Override
        public String getFxmlFile() {
            return "/home.fxml";
        }
    },

    FORGOT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("forgot.title");
        }

        @Override
        public String getFxmlFile() {
            return "/olvideClave.fxml";
        }
    };

    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("config").getString(key);
    }
}
