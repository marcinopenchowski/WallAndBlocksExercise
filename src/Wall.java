import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure{

    private List<Block> blocks = new ArrayList<>();

    public CompositeBlock createCompositeBlock() {
        CompositeBlock compositeBlock = new CompositeBlock() {
            List<Block> blockList = new ArrayList<>();

            @Override
            public List<Block> getBlocks() {
                return blockList;
            }

            @Override
            public void addBlock(Block block) {
                blocks.remove(block);
                blockList.add(block);
            }

            @Override
            public void removeBlock(Block block) {
                blockList.remove(block);
                blocks.add(block);
            }

            @Override
            public int checkSize() {
                int size = 1;
                for(Block block : getBlocks()){
                    if(block instanceof CompositeBlock){
                        size = size + ((CompositeBlock) block).checkSize();
                    } else{
                        size++;
                        }
                    }

                return size;
            }

            @Override
            public Optional findBlockByColor(String color) {
                for(Block block : getBlocks()){
                    if(block instanceof CompositeBlock){
                        if(((CompositeBlock) block).findBlockByColor(color).isPresent()) {
                            return ((CompositeBlock) block).findBlockByColor(color);
                        }
                    }else{
                        if(block.getColor().equals(color)){
                            return Optional.of(block);
                        }
                    }
                }
                return Optional.empty();
            }

            @Override
            public List<Block> findBlocksByMaterial(String material) {
                List<Block> result = new ArrayList<>();
                for(Block block : getBlocks()){
                    if(block instanceof CompositeBlock){
                        result.addAll(((CompositeBlock) block).findBlocksByMaterial(material));
                    }else{
                        if(block.getMaterial().equals(material)){
                            result.add(block);
                        }
                    }

                }


                return result;
            }

            @Override
            public String toString() {
                return "CompositeBlock{" +
                        blockList +
                        '}';
            }

            @Override
            public String getColor() {
                String result = "";
                for (Block block : getBlocks()) {
                    if(block instanceof CompositeBlock){
                        result = result + block.getColor();
                    }else {
                        result = result + block.getColor() + " ";
                    }
                }

                return result;

            }

            @Override
            public String getMaterial() {
                String result = "";
                for (Block block : getBlocks()) {
                    if(block instanceof CompositeBlock){
                        result = result + block.getMaterial();
                    }else {
                        result = result + block.getMaterial() + " ";
                    }
                }
                return result;
            }
        };

        blocks.add(compositeBlock);
        return compositeBlock;
    }

    @Override
    public Optional findBlockByColor(String color) {
        for (Block block : blocks) {
            if(block instanceof CompositeBlock){
                if(((CompositeBlock) block).findBlockByColor(color).isPresent()) {
                    return ((CompositeBlock) block).findBlockByColor(color);
                }
            }
            if(block.getColor().equals(color)){
                return Optional.of(block);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();

        for (Block block : blocks) {
            if(block instanceof CompositeBlock){
                result.addAll(((CompositeBlock) block).findBlocksByMaterial(material));
            }
            if(block.getMaterial().equals(material)){
                result.add(block);
            }
        }
        return result;
    }

    @Override
    public int count() {
        int size = 0;
        for(Block block : blocks){
            if(block instanceof CompositeBlock){
                size = size + ((CompositeBlock) block).checkSize();
            }else{
                size++;
            }
        }

        return size;
    }

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

            @Override
            public String toString() {
                return "Block{" + this.getColor() + ", " + this.getMaterial() + "}";
            }
        };
        blocks.add(block);
        return block;
    }


    // Testing
    public static void main(String[] args) {
        Wall wall = new Wall();

        Block block1 = wall.createBlock("brown", "wood");
        Block block2 = wall.createBlock("grey", "gravel");
        Block block3 = wall.createBlock("white", "ice");
        Block block4 = wall.createBlock("red", "brick");
        Block block5 = wall.createBlock("red", "ice");

        CompositeBlock compositeBlock1 = wall.createCompositeBlock();
        CompositeBlock compositeBlock2 = wall.createCompositeBlock();
        CompositeBlock compositeBlock3 = wall.createCompositeBlock();
        CompositeBlock compositeBlock4 = wall.createCompositeBlock();

        compositeBlock1.addBlock(block4);
        compositeBlock4.addBlock(block3);
        compositeBlock4.addBlock(block2);
        compositeBlock4.addBlock(block1);
        compositeBlock1.addBlock(compositeBlock2);
        compositeBlock2.addBlock(compositeBlock3);
        compositeBlock1.addBlock(compositeBlock4);

        System.out.println("========All Block List");
        System.out.println(wall.blocks);
        System.out.println("Count List: " + wall.count());
        System.out.println();
        System.out.println("========Find Block By Color");
        System.out.println(wall.findBlockByColor("red"));
        System.out.println(wall.findBlockByColor("brown"));
        System.out.println(wall.findBlockByColor("grey"));
        System.out.println(wall.findBlockByColor("white"));
        System.out.println(wall.findBlockByColor("yellow"));
        System.out.println();

        System.out.println("========Find Blocks By Material");
        System.out.println();
        System.out.println(wall.findBlocksByMaterial("ice"));
        System.out.println(wall.findBlocksByMaterial("wood"));
        System.out.println(wall.findBlocksByMaterial("brick"));





    }

}
