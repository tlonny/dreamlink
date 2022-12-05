package dreamlink.state;

public class SimulationState implements ISynchronizedState {

    public static final SimulationState instance = new SimulationState();

    private boolean noClip;
    private boolean showHiddenBlocks;
    private boolean allowOpenDoors;
    private boolean allowCrouch;
    private boolean allowEdit;

    private boolean nextNoClip;
    private boolean nextShowHiddenBlocks;
    private boolean nextAllowOpenDoors;
    private boolean nextAllowCrouch;
    private boolean nextAllowEdit;

    public void setup() {
        SynchronizedStateManager.instance.addState(this);
    }

    public boolean getShowHiddenBlocks() {
        return this.showHiddenBlocks;
    }

    public void setShowHiddenBlocks(boolean canSeeHiddenBlocks) {
        this.nextShowHiddenBlocks = canSeeHiddenBlocks;
    }

    public boolean getNoClip() {
        return this.noClip;
    }

    public void setNoClip(boolean isNoClip) {
        this.nextNoClip = isNoClip;
    }

    public boolean getAllowOpenDoors() {
        return this.allowOpenDoors;
    }

    public void setAllowOpenDoors(boolean canOpenDoors) {
        this.nextAllowOpenDoors = canOpenDoors;
    }

    public boolean getAllowCrouch() {
        return this.allowCrouch;
    }

    public void setAllowCrouch(boolean canCrouch) {
        this.nextAllowCrouch = canCrouch;
    }

    public boolean getAllowEdit() {
        return this.allowEdit;
    }

    public void setAllowEdit(boolean canEdit) {
        this.nextAllowEdit = canEdit;
    }

    @Override
    public void advanceState() {
        this.noClip = this.nextNoClip;
        this.showHiddenBlocks = this.nextShowHiddenBlocks;
        this.allowOpenDoors = this.nextAllowOpenDoors;
        this.allowCrouch = this.nextAllowCrouch;
        this.allowEdit = this.nextAllowEdit;
    }
    
}
