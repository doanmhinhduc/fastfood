package com.example.fastfood.entity;

import com.example.fastfood.annotation.Column;
import com.example.fastfood.annotation.Table;

import java.util.HashMap;

    @Table(name= "Category")
    public class Category {
        @Column(name = "id", type = "INT PRIMARY KEY AUTO_INCREMENT")
        private int id;
        @Column(name = "categoryName", type = "VARCHAR(250)")
        private String categoryName;
        @Column(name = "status", type = "INT DEFAULT 1")
        private int status;

        public Category() {
        }

        public Category(int id, String categoryName, int status) {
            this.id = id;
            this.categoryName = categoryName;
            this.status = status;
        }


        public void checkValid(){
            this.error = new HashMap<>();
            if (this.categoryName==null||this.categoryName.length()==0){
                this.error.put("categoryName","categoryName requied");
            }
        }
        public HashMap<String,String>error;
        public HashMap<String,String>getErrors(){
            checkValid();
            return error;
        }

        public boolean isvalid(){
            checkValid();
            return error ==null||error.size()==0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Category category = (Category) o;
            return id == category.id;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", categoryName='" + categoryName + '\'' +
                    ", status=" + status +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

