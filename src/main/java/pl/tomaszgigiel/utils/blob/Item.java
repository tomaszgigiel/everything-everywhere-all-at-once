package pl.tomaszgigiel.utils.blob;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Item {
	@Getter
	private String id;
	@Getter
	private String name;
	@Getter
	private byte[] contents;
}
