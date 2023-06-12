package br.com.darlison.portifolio.application.model.input;

import br.com.darlison.portifolio.domain.enums.ProjectStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    @NotBlank(message = "Campo name é obrigatório")
    private String name;

    @NotBlank(message = "Campo description é obrigatório")
    private String description;

    @NotNull(message = "Campo begin_date está inválido")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;

    @NotNull(message = "Campo expected_end_date está inválido")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expectedEndDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @NotNull(message = "Campo status está inválido")
    private ProjectStatusEnum status;

    @NotNull(message = "Campo budget está inválido")
    private BigDecimal budget;

    @NotNull(message = "Campo risk está inválido")
    private String risk;

    @NotNull(message = "Campo manager está inválido")
    private UUID manager;

    @NotNull(message = "Campo members está vazio")
    private List<UUID> members;
}
