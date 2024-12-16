module com.text.text_processing_tool {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.text.text_processing_tool to javafx.fxml;
    exports com.text.text_processing_tool;
    exports com.text.text_processing_tool.utils;
    opens com.text.text_processing_tool.utils to javafx.fxml;
    exports com.text.text_processing_tool.service;
    opens com.text.text_processing_tool.service to javafx.fxml;
    exports com.text.text_processing_tool.models;
    opens com.text.text_processing_tool.models to javafx.fxml;
    exports com.text.text_processing_tool.views to javafx.fmxl;
    opens com.text.text_processing_tool.views to javafx.fxml;
}