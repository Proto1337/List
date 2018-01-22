public class ErwList extends List
{
    /**
     * Konstruktor
     */
    public ErwList()
    {}

    /**
     * Zwei Listen werden zusammengefügt.
     * @param pListe     Liste, die eingefügt wird
     */
    public void concat(List pList)
    {
        if (pList != null) {
            while ( ! pList.isEmpty()) {
                this.append(pList.front().getContent());
                pList.toFirst();
                pList.remove();
            }
        }
    }
}
