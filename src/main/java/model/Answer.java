package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE);
@DiscriminatorColumn(name = "ITEM_TYPE");
public class Answer extends AbstractEntity{
    private int id;
    private int owner;
    private int question;
    private String answer;
}
