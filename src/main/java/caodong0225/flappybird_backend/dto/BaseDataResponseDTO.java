package caodong0225.flappybird_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jyzxc
 * @since 2024-10-26
 */
@Getter
@Setter
public class BaseDataResponseDTO extends BaseResponseDTO {
    @Schema(description = "响应数据")
    private Object data;

    public BaseDataResponseDTO() {
        super();
    }

    public BaseDataResponseDTO(Object data) {
        super();
        this.data = data;
    }

    public BaseDataResponseDTO(Integer code, String message) {
        super(code, message);
    }

    public BaseDataResponseDTO(Integer code, String message, Object data) {
        super(code, message);
        this.data = data;
    }
}
