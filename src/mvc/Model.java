package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {
    private boolean unsavedChanges = false;
    private String fileName = null;

    public void changed() {
        this.unsavedChanges = true;
        notifySubscribers("Changes have been made");
    }

    public String getFileName() {
        notifySubscribers("");
        return this.fileName;
    }
    public void setFileName(String newFileName) {
        this.fileName = newFileName;
        notifySubscribers("File name set");
    }

    public boolean getUnsavedChanges() {
        notifySubscribers("");
        return this.unsavedChanges;
    }
    public void setUnsavedChanges(boolean changes) {
        this.unsavedChanges = changes;
        notifySubscribers("Save status updated");
    }

}
