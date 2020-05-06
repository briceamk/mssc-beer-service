package cm.amk.msscbeer.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
        if (timestamp != null) {
            return OffsetDateTime.of(timestamp.toLocalDateTime().toLocalDate(), timestamp.toLocalDateTime().toLocalTime(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()) : null;
    }
}
