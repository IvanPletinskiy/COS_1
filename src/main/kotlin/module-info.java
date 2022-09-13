module com.handen.cos.cos_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.handen.cos.cos_1 to javafx.fxml;
    exports com.handen.cos.cos_1;
    opens com.handen.saimmot to javafx.fxml;
    exports com.handen.saimmot;
}