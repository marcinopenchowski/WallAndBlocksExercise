import java.util.List;
import java.util.Optional;

interface CompositeBlock extends Block {
    List<Block> getBlocks();
    void addBlock(Block block);
    void removeBlock(Block block);
    int checkSize();
    Optional findBlockByColor(String color);
    public List<Block> findBlocksByMaterial(String material);
}