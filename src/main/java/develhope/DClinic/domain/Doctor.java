package develhope.DClinic.domain;

import jakarta.persistence.*;
    @Entity
    @Table(name = "Doctors")
    public class Doctor {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "patient_id")
        private long id;

        @Column(name = "name")
        private String name;

        @Column(name = "lastname")
        private String lastname;

        @Column(name = "specialisation")
        private String specialisation;

        @Column(name = "review")
        private String review;

        public Doctor() {
        }

        public Doctor(long id, String name, String lastname, String specialisation, String review) {
            this.id = id;
            this.name = name;
            this.lastname = lastname;
            this.specialisation = specialisation;
            this.review = review;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getSpecialisation() {
            return specialisation;
        }

        public void setSpecialisation(String specialisation) {
            this.specialisation = specialisation;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
    }
