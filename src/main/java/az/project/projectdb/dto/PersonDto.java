package az.project.projectdb.dto;

import com.sun.istack.NotNull;
import lombok.NonNull;

import java.util.Date;

public record PersonDto(
        @NotNull
        Long id,
         String name,
         String surname,
         String middleName,
         Long sex,
         Long comPersonUniqId,
         Long active,
         Long notificationStatus,
         Long newC,
         String oldUsername) {
}
