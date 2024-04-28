import java.util.Random;
public class AutomatedPlayer extends Player {

    private String jumpIntent;

    private char[] legalActions;

    private Random random;

    private String[] areaNames = { "swamp", "forest", "cave", "ruins" };
    public AutomatedPlayer(PlayerMap gameMap) {
        super(gameMap);
        this.jumpIntent = "";
        this.legalActions = new char[]{'N', 'A', 'P', 'D', 'J'};
    }

    @Override
    public void takeTurn()
    {
        if (gameMap.currentNode != null && gameMap.currentNode.nodeData.length > gameMap.currentIndexInNode
                && gameMap.currentNode.nodeData[gameMap.currentIndexInNode] != null)
        {

            GameObject currentObject = (GameObject) gameMap.currentNode.nodeData[gameMap.currentIndexInNode];
            String message = currentObject.visit();
            processMessage(message);
        }

        // Decision making for the next action based on processed clues
        decideNextAction();
    }

    private void processMessage(String message) {
        if (message.startsWith("A clue: Location is: not"))
        {
            // Remove this area from possible areas
            String area = message.substring(25, message.length() - 1);
            for(int i = 0; i < areaNames.length;i++)
            {
                if(areaNames[i].equals(area))
                {
                    areaNames[i] = "";
                }
            }
            resizeArray();

        } else if (message.startsWith("A clue: Location is:")) {
            // Narrow down to this area
            String area = message.substring(21, message.length() - 1);
            for(int i = 0; i < areaNames.length;i++)
            {
                if(!areaNames[i].equals(area))
                {
                    areaNames[i] = "";
                }
                else{
                    areaNames[0] = areaNames[i];
                }
            }
            this.jumpIntent = area;
        }
        else if(message.startsWith("An empty space"))
        {
            //Some code here
        }
        else if(message.startsWith("The treasure!"))
        {
            //some code here
        }
    }

    private void decideNextAction() {
        // If we have narrowed down to one area then select it
        if (areaNames.length == 1 && !gameMap.currentAreaName.equals(this.jumpIntent))
        {
            gameMap.takeAction(PlayerMap.Action.J);
            return; // Stop here to avoid making another move in the same turn
        }
        else if (gameMap.currentAreaName.equals(""))
        {
            // If we're not in any area, jump to a default area
            this.jumpIntent = "swamp"; // Default starting area or choose randomly
            gameMap.takeAction(PlayerMap.Action.J);
            return; // Stop here to avoid making another move in the same turn
        }

        PlayerMap.Action[] actions = PlayerMap.Action.values();
        PlayerMap.Action action = null;
        boolean valid = false;
        while (!valid)
        {
            valid = true;
            int actionIndex = random.nextInt(actions.length - 1);
            action = actions[actionIndex];
            switch (action) {
                case N:
                    valid = canMoveNext();
                    break;
                case P:
                    valid = canMovePrev();
                    break;
                case A:
                    valid = canAscend();
                    break;
                case D:
                    valid = canDescend();
                    break;
                case J:
                    valid = false;
                    break;
            }
        }
        // Execute the action
        gameMap.takeAction(action);
    }

    private void resizeArray()
    {
        if(areaNames.length>1)
        {
            String[] temp = new String[areaNames.length-1];
            int j = 0;
            for(int i = 0; i < areaNames.length-1;i++)
            {
                if(areaNames[i].equals(""))
                {
                    //Empty place
                    j++;
                }
                temp[i] = areaNames[j];
            }

            areaNames = new String[temp.length];

            for(int i = 0; i < temp.length;i++)
            {
                areaNames[i] = temp[i];
            }
        }
    }

    @Override
    public void selectArea() {
        gameMap.selectArea(jumpIntent);
    }

    @Override
    public void close() {
        // Cleanup anything you use here
        // HumanPlayer uses this to close its scanner
    }

    private boolean canMoveNext() {
        return gameMap.currentNode != null && gameMap.currentIndexInNode + 1 < gameMap.currentNode.nodeData.length;
    }

    private boolean canMovePrev() {
        return gameMap.currentNode != null && gameMap.currentIndexInNode - 1 >= 0;
    }

    private boolean canAscend() {
        return gameMap.currentNode != null && gameMap.currentNode.parent != null;
    }

    private boolean canDescend() {
        return gameMap.currentNode != null && gameMap.currentNode.descend(gameMap.currentIndexInNode) != null;
    }

}
