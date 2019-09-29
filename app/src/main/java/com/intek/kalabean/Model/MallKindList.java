package com.intek.kalabean.Model;

import java.util.List;

public class MallKindList {
    private List<MallKind> items;

    public List<MallKind> getItems() {
        return items;
    }

    public void setItems(List<MallKind> items) {
        this.items = items;
    }

    public class MallKind {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
