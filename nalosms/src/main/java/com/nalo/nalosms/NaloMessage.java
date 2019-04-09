package com.nalo.nalosms;

public class NaloMessage {

    private String username, message, source, password, dlr;
    private int type;
    private String[] destination;


    private NaloMessage() {
    }

    public static class Builder {

        private NaloMessage message;

        public Builder() {
            this.message = new NaloMessage();
        }


        /**
         * Set Username & Password needed for Authentication
         *
         * @param username nalo-sms portal username
         * @param password nalo-sms portal password
         */
        public Builder withUsernameAndPassword(String username, String password) {
            this.message.username = username;
            this.message.password = password;
            return this;
        }

        /**
         * Set the message content
         *
         * @param message your message
         */
        public Builder setMessage(String message) {
            this.message.message = message;
            return this;
        }


        /**
         * Set the destination of the message
         *
         * @param destination message destination
         */
        public Builder setDestination(String... destination) {
            this.message.destination = destination;
            return this;
        }

        /**
         * Set the message source
         *
         * @param source your message
         */
        public Builder setSource(String source) {
            this.message.source = source;
            return this;
        }

        /**
         * Set the message type
         *
         * @param type your message type
         */
        public Builder setType(int type) {
            this.message.type = type;
            return this;
        }

        /**
         * Set Whether Delivery Report is required
         *
         * @param required
         */
        public Builder withDeliveryReport(boolean required) {
            this.message.dlr = required ? "1" : "0";
            return this;
        }

        public NaloSMS build() {
            return new NaloSMS(message);
        }

    }

}
