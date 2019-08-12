package com.aditya.heeliumapp.Pojos;

/**
 * Created by aditya on 12/30/17.
 */

public class DocumentUploadRequest {

    String brand;
    String about;
    String path;
    String price;
    String color;
    String size;

    boolean firm_heel_support;
    boolean arch_support;
    boolean heel_cushioning;
    boolean toe_cushioning;
    boolean arched_insoles;
    boolean flexi_cuts_on_outsole;
    boolean toe_cap;
    boolean high_in_linear_cushion_at_heel;
    boolean wide_ball_area;
    boolean half_sizes_in_product;
    boolean different_toe_box_sizes_available;
    boolean colors_choice_available;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFirm_heel_support(boolean firm_heel_support) {
        this.firm_heel_support = firm_heel_support;
    }

    public void setArch_support(boolean arch_support) {
        this.arch_support = arch_support;
    }

    public void setHeel_cushioning(boolean heel_cushioning) {
        this.heel_cushioning = heel_cushioning;
    }

    public void setToe_cushioning(boolean toe_cushioning) {
        this.toe_cushioning = toe_cushioning;
    }

    public void setArched_insoles(boolean arched_insoles) {
        this.arched_insoles = arched_insoles;
    }

    public void setFlexi_cuts_on_outsole(boolean flexi_cuts_on_outsole) {
        this.flexi_cuts_on_outsole = flexi_cuts_on_outsole;
    }

    public void setToe_cap(boolean toe_cap) {
        this.toe_cap = toe_cap;
    }

    public void setHigh_in_linear_cushion_at_heel(boolean high_in_linear_cushion_at_heel) {
        this.high_in_linear_cushion_at_heel = high_in_linear_cushion_at_heel;
    }

    public void setWide_ball_area(boolean wide_ball_area) {
        this.wide_ball_area = wide_ball_area;
    }

    public void setHalf_sizes_in_product(boolean half_sizes_in_product) {
        this.half_sizes_in_product = half_sizes_in_product;
    }

    public void setDifferent_toe_box_sizes_available(boolean different_toe_box_sizes_available) {
        this.different_toe_box_sizes_available = different_toe_box_sizes_available;
    }

    public void setColors_choice_available(boolean colors_choice_available) {
        this.colors_choice_available = colors_choice_available;
    }
}

