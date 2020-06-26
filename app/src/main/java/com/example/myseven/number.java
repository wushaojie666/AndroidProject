package com.example.myseven;

public class number {


        public  String name;
        public String phone;
        public  int id;


        public number(){
            super();
        }
        public number(int id, String name, String phone){
            this.id=id;
            this.name=name;

            this.phone=phone;
        }

        public int getId(){ return id; }
        public String getName(){
            return name;
        }

        public String getPhone(){
            return  phone;
        }

    }

