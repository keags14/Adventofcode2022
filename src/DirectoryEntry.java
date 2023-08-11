import java.util.*;

public class DirectoryEntry extends FileEntry{
    private final DirectoryEntry parent;
    private Map<String, FileEntry> fileEntryList = new LinkedHashMap();
    public DirectoryEntry(DirectoryEntry parent, String name) {
        super(name, 0);
        this.parent = parent;
    }

    public Collection<FileEntry> getFileEntryList() {
        return fileEntryList.values();
    }

    public void addFile(FileEntry fe) {
        fileEntryList.put(fe.getName(), fe);
    }

    public long size() {
        long count = 0;
        for (FileEntry fe: fileEntryList.values()) {
            count += fe.size();
        }
        return count;
    }

    public DirectoryEntry getParent() {
        return parent;
    }

    public DirectoryEntry getDir(String s) {
        return (DirectoryEntry) fileEntryList.get(s);
    }
}
