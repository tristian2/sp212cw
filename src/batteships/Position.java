/*
 * Class Position
 * 
 */
package batteships;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author MARGARET WRIGHT
 * @author keith
 */

@RequiredArgsConstructor
public class Position {

    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int x;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private int y;
}
