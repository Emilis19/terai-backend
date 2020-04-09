package com.academy.terai.model;

import com.academy.terai.model.request.CommentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String hrId;
    private String hrName;
    @NotBlank(message = "komentaras negali būti tuščias")
    private String comment;
    private Date dateCreated;

    public Comment(CommentRequest request){
        this.hrId = request.getHrId();
        this.comment = request.getComment();
        this.dateCreated = new Date();
    }

}
