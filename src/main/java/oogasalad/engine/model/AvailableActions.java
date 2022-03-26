@FunctionalInterface
public interface AvailableActions {

    public Action[] availableActions(Board board, Player player, Rule[]);

}
available actions -> a function/mapping between a state of the board, a player, and a list of rules that returns a list of actions
