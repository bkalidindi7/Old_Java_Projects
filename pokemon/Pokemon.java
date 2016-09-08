/**
 * Represents a Pokemon object. Each has a number, a name, and two elemental
 * types, chosen from the PokemonType enumeration.
 *
 * @author  Joe Rossi
 * @version 1.0
 */
public class Pokemon implements Comparable<Pokemon> {

    // ------Instance data here------
    private int pokeNum;
    private String name;
    private PokemonType type1;
    private PokemonType type2;

    /**
     * Constructs a Pokemon object
     *
     * @param num   this Pokemon's unique number
     * @param name  this Pokemon's name
     * @param p this Pokemon's primary type
     * @param s this Pokemon's secondary type
     */
    public Pokemon(int num, String name, PokemonType p, PokemonType s) {
        pokeNum = num;
        this.name = name;
        type1 = p;
        type2 = s;
    }

    @Override
    public int compareTo(Pokemon o) {
        return this.pokeNum - o.pokeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon that = (Pokemon) o;
        return this.pokeNum == (that.pokeNum);
    }

    @Override
    public int hashCode() {
        return pokeNum;
    }

    @Override
    public String toString() {
        return String.format(
                    "# %5d: %-15s Primary Type: %-15s Secondary Type: %s",
                    pokeNum, name, type1, type2);
    }

    /**
     * @return  the name of this Pokemon
     */
    public String getName() {
        return name;
    }

    /**
     * @return  the unique number of this Pokemon
     */
    public int getNumber() {
        return pokeNum;
    }

    /**
     * @return  the primary type of this Pokemon
     */
    public PokemonType getPrimaryType() {
        return type1;
    }

    /**
     * @return  the secondary type of this Pokemon
     */
    public PokemonType getSecondaryType() {
        return type2;
    }
}
