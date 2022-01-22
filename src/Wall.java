import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure{

    private List<Block> blocks = new ArrayList<>();

    // Creating Composite Block
    public CompositeBlock createCompositeBlock(){
        CompositeBlock compositeBlock = new CompositeBlock() {
            List<Block> blockList = new ArrayList<>();

            @Override
            public List<Block> getBlocks() {
                return blockList;
            }

            @Override
            public String getColor() {
                String result = "";
                for (Block block : getBlocks()) {
                    result = result + block.getColor() + "\n";
                }

                return result;
            }

            @Override
            public String getMaterial() {
                String result = "";
                for (Block block : getBlocks()) {
                    result = result + block.getMaterial() + "\n";
                }
                return result;
            }
        };
        return compositeBlock;
    }

    @Override
    public Optional findBlockByColor(String color) {
        for (Block block : blocks) {
            if(block.getColor().equals(color)){
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();

        for (Block block : blocks) {
            if(block.getMaterial().equals(material)){
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        return blocks.size();
    }

    // Creating Block
    public Block createBlock(String color, String material){
        Block block = new Block() {
            @Override
            public String getColor() {
                return color;
            }

            @Override
            public String getMaterial() {
                return material;
            }
        };
        return block;
    }
    // Adding Block to Wall
    public void addBlock(String color, String material){
        blocks.add(createBlock(color, material));
    }
    // Adding Block to Wall and Composite Block
    public void addBlock(CompositeBlock compositeBlock, String color, String material){
        addBlock(color,material);
        compositeBlock.getBlocks().add(createBlock(color, material));
    }

    // Test
    public static void main(String[] args) {
        Wall wall = new Wall();
        CompositeBlock compositeBlock = wall.createCompositeBlock();
        wall.addBlock(compositeBlock, "brown", "wood");
        wall.addBlock(compositeBlock, "yellow", "gravel");
        wall.addBlock("black", "gravel");

        System.out.println(compositeBlock.getMaterial());
        System.out.println(wall.findBlocksByMaterial("gravel"));
        System.out.println(wall.findBlockByColor("black"));
        System.out.println(compositeBlock.getBlocks().get(0).getColor());
    }

}
