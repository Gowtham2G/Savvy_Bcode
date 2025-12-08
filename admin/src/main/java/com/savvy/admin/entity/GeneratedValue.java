package com.savvy.admin.entity;

import jakarta.persistence.GenerationType;

public @interface GeneratedValue {

	GenerationType strategy();

}
