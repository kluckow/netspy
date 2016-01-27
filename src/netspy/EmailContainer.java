package netspy;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class EmailContainer.
 */
public class EmailContainer {

    /** The mails. */
    private List<Email> mails = new ArrayList<>();

    /**
     * Gets the mails.
     *
     * @return the mails
     */
    public List<Email> getMails() {

        return mails;
    }

    /**
     * Sets the mails.
     *
     * @param mails the new mails
     */
    public void setMails(final List<Email> mails) {

        this.mails = mails;
    }

}
