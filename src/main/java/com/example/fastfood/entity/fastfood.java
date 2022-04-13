package com.example.fastfood.entity;

import com.example.fastfood.annotation.Column;
import com.example.fastfood.annotation.Table;
import com.example.fastfood.modelAnnotation.ForeignKey;
import com.example.fastfood.modelAnnotation.Id;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

    @Table(name = "fastfood")
    public class fastfood {
        @Id(AutoIncrement = true)
        @Column(name = "id", type = "INT PRIMARY KEY AUTO_INCREMENT")
        private int id;
        @Column(name = "name", type = "VARCHAR(250)")
        private String name;
        @ForeignKey(referenceColumn = "id", referenceTable = "categories")
        @Column(name = "categoryId", type = "INT")
        private int categoryId;
        @Column(name = "description", type = "TEXT(250)")
        private String description;
        @Column(name = "thumbnail", type = "TEXT")
        private String thumbnail;
        @Column(name = "price", type = "DOUBLE")
        private double price;
        @Column(name = "createdAt", type = "TIMESTAMP NOT NULL DEFAULT CURRENT_DATE()")
        private Timestamp createdAt;
        @Column(name = "updatedAt", type = "TIMESTAMP NOT NULL DEFAULT CURRENT_DATE()")
        private Timestamp updatedAt;
        @Column(name = "status", type = "INT DEFAULT 1")
        private int status;
        private HashMap<String, String> errors;

        public fastfood() {
            this.status=1;
        }

        public fastfood(String name, int categoryId, String description, String thumbnail, double price, Timestamp createdAt, Timestamp updatedAt, int status) {
            this.name = name;
            this.categoryId = categoryId;
            this.description = description;
            this.thumbnail = thumbnail;
            this.price = price;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.status = status;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }


        public void checkValid(){
            this.error = new HashMap<>();
            if (this.name==null||this.name.length()==0){
                this.error.put("name","name requied");
            }
            if (this.thumbnail==null||this.thumbnail.length()==0){
                this.error.put("thumbnail","thumbnailrequied");
            }
            if (this.price==0){
                this.error.put("price","price requied");
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
            fastfood food = (fastfood) o;
            return id == food.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public fastfood(String name, int categoryId, String description, String thumbnail, double price, int status) {
            this.name = name;
            this.description = description;
            this.thumbnail = thumbnail;
            this.price = price;
            this.status = status;
        }

        public fastfood(int id, String name, Integer categoryId, String description, String thumbnail, double price, Timestamp createdAt, Timestamp updatedAt, int status) {
            this.id = id;
            this.name = name;
            this.categoryId = categoryId;
            this.description = description;
            this.thumbnail = thumbnail;
            this.price = price;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Food{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", categoryId=" + categoryId +
                    ", description='" + description + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", price=" + price +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", status=" + status +
                    '}';
        }

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

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
        }

        public Timestamp getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
