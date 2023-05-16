package com.swan.poi.enums;

public enum Align {
        AUTO(0), LEFT(1), CENTER(2), RIGHT(3);
        private final int value;

        Align(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }