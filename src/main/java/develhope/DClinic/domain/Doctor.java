package develhope.DClinic.domain;

import jakarta.persistence.*;
/**
 * @author Luca Giorgi
 * Entità del dottore
 */
    @Entity
    @Table
    public class Doctor extends Employee{

        private String review;

        public Doctor() {
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
    }
