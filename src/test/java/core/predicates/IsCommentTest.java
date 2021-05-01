package core.predicates;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsCommentTest {
    private IsComment sut;
    @BeforeEach
    public void setUp() {
        sut = new IsComment();
    }

    @Test
    void predicate() {
        assertTrue(sut.predicate("-- I'm comment"));
        assertTrue(sut.predicate("       -- space"));
        assertTrue(sut.predicate("------------------"));
        assertFalse(sut.predicate("     -    - not comment"));
        assertFalse(sut.predicate("not comment"));
    }
}