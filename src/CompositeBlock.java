import java.util.List;

interface CompositeBlock extends Block {
    List<Block> getBlocks();
    void addBlock(Block block);
    void removeBlock(Block block);
    int checkSize();
}