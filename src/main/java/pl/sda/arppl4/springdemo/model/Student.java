package pl.sda.arppl4.springdemo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@ApiModel(value = "Student", description = "Encja reprezentująca instancję studenta w bazie danych.")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    // imie, nazwisko, numer indeksu, data urodzenia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(example = "1", value = "Identyfikator student w bazie danych.")
    private Long id;

    @ApiModelProperty(example = "Jan", value = "Imie studenta.")
    private String name;

    @ApiModelProperty(name = "Nazwisko", example = "Kowalski", value = "Nazwisko studenta.")
    private String surname;

    @ApiModelProperty(name = "Numer indeksu", example = "030405", value = "Numer indeksu studenta. Uwaga! nie mylić z identyfikatorem!")
    private String indexNumber;

    @ApiModelProperty(example = "2002-03-04", value = "Data urodzenia studenta w formacie yyyy-MM-dd.")
    private LocalDate birthDate;
}
