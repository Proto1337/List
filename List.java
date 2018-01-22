import java.util.Scanner;

public class List<ContentType>
{
    private ListNode first, last, current;
    protected class ListNode
    {
        private ListNode prevNode, nextNode;
        private ContentType currentObject;

        public ListNode(ContentType pContent)
        {
            currentObject = pContent;
            nextNode = null;
            prevNode = null;
        }

        public ListNode(ContentType pContent, ListNode pNextNode, ListNode pPrevNode)
        {
            currentObject = pContent;
            nextNode = pNextNode;
            prevNode = pPrevNode;
        }

        public ContentType getContent()
        {
            return currentObject;
        }

        public void setContent(ContentType pContent)
        {
            currentObject = pContent;
        }

        public ListNode getNext()
        {
            return nextNode;
        }

        public void setNext(ListNode pNextNode)
        {
            nextNode = pNextNode;
        }

        public void setPrev(ListNode pPrevNode)
        {
            prevNode = pPrevNode;
        }

        public ListNode getPrev()
        {
            return prevNode;
        }
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        List<String> list = new List();

        while(true)
        {
            System.out.println();
            System.out.print("Methode eingeben (break zum Abbrechen): ");

            String input = s.next();
            if(input.equals("break"))
            {
                System.out.println(" - - - Programm beendet - - - ");
                break;
            }

            if(input.equals("isEmpty"))
                System.out.println(list.isEmpty());

            if(input.equals("hasAccess"))
                System.out.println(list.hasAccess());

            if(input.equals("toFirst"))
            {
                System.out.println("current wurde auf das erste Objekt der Liste gesetzt.");
                list.toFirst();
            }

            if(input.equals("toLast"))
            {
                System.out.println("current wurde auf das letzte Objekt der Liste gesetzt.");
                list.toLast();
            }

            if(input.equals("next"))
                list.next();

            if(input.equals("back"))
                list.back();    

            if(input.equals("getContent"))
                System.out.println("Der Content von current ist: " + list.getContent() + ".");

            if(input.equals("clear"))
            {
                list.clear();
                System.out.println("Die Liste wurde geleert.");
            }

            if(input.equals("remove"))
            {
                System.out.println(list.getContent() + " wurde aus der Liste entfernt.");
                list.remove();
            }

            if(input.equals("setContent"))
            {
                System.out.print("Content eingeben (break zum Abbrechen): ");
                String pContent = s.next();
                if(pContent.equals("break"))
                    break;
                list.setContent(pContent);
                System.out.println("Der Content von current wurde zu " + pContent + " geändert.");
            }

            if(input.equals("append"))
            {
                System.out.print("Content eingeben (break zum Abbrechen): ");
                String pContent = s.next();
                if(pContent.equals("break"))
                    break;
                list.append(pContent);
                System.out.println(pContent + " wurde am Ende der Liste eingefügt.");
            }

            if(input.equals("insert"))
            {
                System.out.print("Content eingeben (break zum Abbrechen): ");
                String pContent = s.next();
                if(pContent.equals("break"))
                    break;
                list.insert(pContent);
                System.out.println(pContent + " wurde eingefügt.");
            }

            if(input.equals("printList"))
            {
                System.out.print("Der Inhalt der Liste ist: ");
                list.printList();
                System.out.println();
            }
        }
    }

    public List(){}

    /**
     * Es wird überprüft, ob die Liste leer ist.
     * 
     * @return      true, wenn Liste leer / false, wenn Liste Inhalt hat
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Es wird überprüft, ob ein current gesetzt ist.
     * 
     * @return      true, falls current gesetzt / false, wenn nicht
     */
    public boolean hasAccess()
    {
        return current != null;
    }

    /**
     * current wird auf das erste Objekt der Liste gesetzt.
     */
    public void toFirst()
    {
        if(!this.isEmpty())
            current = first;
    }

    /**
     * current wird auf das letzte Objekt der Liste gesetzt.
     */
    public void toLast()
    {
        if(!this.isEmpty())
            current = last;
    }

    /**
     * current wird auf das nächste Objekt gesetzt, bleibt aber unverändert, wenn current bereits auf dem letzten Objekt gesetzt ist.
     */
    public void next()
    {
        if(this.hasAccess())
            if(current != last)
                current = current.getNext();
    }

    /**
     * current wird auf das vorherige Objekt gesetzt, bleibt aber unverändert, wenn current bereits auf dem ersten Objekt gesetzt ist.
     */
    public void back()
    {
        if(this.hasAccess())        
            if(current != first)
                current = current.getPrev();        
    }

    /**
     * Der Content von current wird ausgegeben.
     * 
     * @return      Content von current / null, falls kein Content vorhanden
     */
    public ContentType getContent()
    {
        if(this.hasAccess())
            return current.getContent();
        return null;
    }

    /**
     * Der Content von current wird zu pContent abgeändert.
     * 
     * @param pContent      neuer Content von current
     */
    public void setContent(ContentType pContent)
    {
        if(this.hasAccess())
            current.setContent(pContent);
    }

    /**
     * pContent wird an das Ende der Liste eingefügt.
     * 
     * @param pContent      neuer Content der Liste
     */
    public void append(ContentType pContent)
    {
        if(pContent != null)
        {
            ListNode newNode = new ListNode(pContent);
            if(!isEmpty())
            {
                last.setNext(newNode);
                newNode.setPrev(last);
                last = newNode;
            }
            else
            {
                first = newNode;
                last = newNode;
            }
        }
    }

    /**
     * pContent wird vor current in der Liste eingefügt.
     * 
     * @param pContent      neuer Content der Liste
     */
    public void insert(ContentType pContent)
    {
        if(pContent != null)
        {
            ListNode newNode = new ListNode(pContent);
            if(this.hasAccess())
            {
                if(current == first)
                {
                    newNode.setNext(current);
                    newNode.setPrev(current.getPrev());
                    current.setPrev(newNode);
                    first = newNode;
                }
                else
                {
                    newNode.setNext(current);
                    newNode.setPrev(current.getPrev());
                    current.getPrev().setNext(newNode);
                    current.setPrev(newNode);
                }
            }
            else
                append(pContent);
        }
    }

    /**
     * Das aktuelle current wird gelöscht und current wird auf das nächste Objekt in der Liste gesetzt, 
     * sollte current auch last gewesen sein, so wird current auf das aktuelle last gesetzt.
     */
    public void remove() 
    {
        if(this.hasAccess())
        {
            if(current == first)
            {
                if(current == last)
                {
                    current = null;
                    last = null;
                    first = null;
                    return;
                }
                else
                {
                    first = current.getNext();
                    current.getNext().setPrev(null);
                    current = first;
                    return;
                }
            }            
            else if(current == last)
            {
                if(current == first)
                {
                    current = null;
                    first = null;
                    last = null;
                    return;
                }
                else
                {
                    last = current.getPrev();
                    current.getPrev().setNext(null);
                    current = last;
                    return;
                }
            }
            else if(current != first && current != last)
            {                                    
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                current = current.getNext();
                return;
            }
        }
    }

    /**
     * Erster Knoten der Liste wird ausgegeben.
     */
    protected ListNode front()
    {
        return first;
    }

    /**
     * Die komplette Liste wird gelöscht.
     */
    public void clear()
    {
        while(!this.isEmpty())
        {
            this.toFirst();
            remove();
        }
        current = null;
    }

    /**
     * Die komplette Liste wird in der Konsole ausgegeben.
     */
    public void printList()
    {
        ListNode a = new ListNode(null);
        a = first;
        System.out.print("[ ");
        while(a != null)
        {
            System.out.print(a.getContent() + " ");
            a = a.getNext();
        }
        System.out.println("]");
    }
}