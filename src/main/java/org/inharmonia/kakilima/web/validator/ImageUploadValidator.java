package org.inharmonia.kakilima.web.validator;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.util.Iterator;

public class ImageUploadValidator extends AbstractValidator<FileUpload> {
    private String type;

    public ImageUploadValidator(String type){
        assert type != null;
        this.type = type;
    }
    
    @Override
    protected void onValidate(IValidatable<FileUpload> fileUploadValidatable) {
        try {
            //TODO content based validation doesn't work
//            Iterator<ImageReader> readers = ImageIO.getImageReaders(fileUploadValidatable.getValue().getInputStream());
//            if (!readers.hasNext()) {
//                error(fileUploadValidatable);
//            } else {
//                ImageReader reader = readers.next();
//                if (!type.equalsIgnoreCase(reader.getFormatName())) {
//                    error(fileUploadValidatable);
//                }
//            }
            if(!"image/jpeg".equals(fileUploadValidatable.getValue().getContentType())){
                error(fileUploadValidatable);       
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
