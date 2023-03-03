package entities;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * TransactionList class represents a whole list of recorded Transactions and a
 * suite of tools to present them.
 *
 * @author SPAM (Sammy Dinka, Andrey Yefermov, Pavel Danek) © 2023
 *
 */
public class TransactionList {

    private ArrayList<Transaction> transactionList;

    /**
     * The constructor.
     */
    public TransactionList() {
        transactionList = new ArrayList<Transaction>();
    }

    /**
     * Method allows to add a single transaction at the end of the list.
     *
     * @param transaction - the Transaction being added
     * @return TRUE - if everything goes well FALSE - if there's trouble and
     *         Transaction has NOT been added
     */
    public boolean add(Transaction transaction) {
        return transactionList.add(transaction);
    }

    /**
     * Removes a Transaction, identified by its reference number, from the list.
     *
     * @param refNumber - a reference number of the Transaction to be removed
     * @return TRUE - if everything goes well FALSE - if there's trouble and
     *         Transaction has NOT been removed
     */
    public boolean remove(String refNumber) {
        for (Transaction transaction : transactionList) {
            if (transaction.getRefNumber().equalsIgnoreCase(refNumber)) {
                return transactionList.remove(transaction);
            }
        }
        return false;
    }

    /**
     * Gets a Transaction at <index> position in the Transaction list.
     * @param index - position in the list
     * @return - seeked Transaction
     */
    public Transaction get(int index) {
        return transactionList.get(index);
    }

    /**
     * Method places a new Transaction ArrayList into its main container,
     * transactionList, if its empty or null.
     *
     * @param list - the ArrayList to be placed in transactionList
     * @return TRUE - if everything goes well FALSE - if there's trouble and
     *         transactionList has NOT been filled
     */
    public boolean fillTransactionList(ArrayList<Transaction> list) {
        if (transactionList == null)
            transactionList = new ArrayList<Transaction>();
        if (transactionList.size() > 0)
            return false;
        transactionList = list;
        return true;
    }

    /**
     * Method clears (empties out) transactionList.
     */
    public void clearTransactionList() {
        transactionList = new ArrayList<Transaction>();
    }

    /**
     * Returns a ListIterator (advanced iterator, capable of traversing a list in
     * both directions) of transactionList.
     */
    public ListIterator<Transaction> listIterator() {
        return transactionList.listIterator();
    }

    /**
     * Finds and fetches a Transaction, identified by its reference number, from
     * transactionList.
     *
     * @param refNumber - reference number of Transaction being searched for
     * @return Transaction in question, or NULL if not found
     */
    public Transaction searchByRefNumber(String refNumber) {
        for (Iterator<Transaction> i = listIterator(); i.hasNext();) {
            Transaction t = i.next();
            if (t.getRefNumber().equalsIgnoreCase(refNumber)) {
                return t;
            }
        }
        return null;
    }

//	For sorting we used merge sort algorithm. It's FAST. The original idea was to reuse the code,
//	so that there is not so much repetition. But that would mean to move the switch statement,
//	which decides by WHAT parameter/ field we'll be sorting, deep into the sorting algorithm
//	and make the same decision many, many times, and thereby slow the process down. Instead, we put
//	the switch statement in the public sort method, so that the decision only has to be made once.
//	As a byproduct, the code MUST repeat, unfortunately.
//
//	In each pair of methods, the first (mergeByXXXX) does the actual sorting; the second, main method
//	(mergeSortByXXXX), divides the ArrayList recursively into its smallest parts and THEN calls the
//	first, sorting method.
//
//  --------------- sort by posted date -------------------------------------------

    private ArrayList<Transaction> mergeByDate(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).comparePostedDates(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByDate(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByDate(list1);
        list2 = mergeSortByDate(list2);
        return mergeByDate(list1, list2);
    }

//  --------------- sort by reference number --------------------------------------

    private ArrayList<Transaction> mergeByRef(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).compareRefNumbers(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByRef(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByRef(list1);
        list2 = mergeSortByRef(list2);
        return mergeByRef(list1, list2);
    }

//  --------------- sort by description -------------------------------------------

    private ArrayList<Transaction> mergeByDescription(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).compareDescriptions(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByDescription(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByDescription(list1);
        list2 = mergeSortByDescription(list2);
        return mergeByDescription(list1, list2);
    }

//  --------------- sort by memo --------------------------------------------------

    private ArrayList<Transaction> mergeByMemo(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).compareMemos(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByMemo(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByMemo(list1);
        list2 = mergeSortByMemo(list2);
        return mergeByMemo(list1, list2);
    }

//  --------------- sort by amount ------------------------------------------------

    private ArrayList<Transaction> mergeByAmount(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).compareAmounts(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByAmount(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByAmount(list1);
        list2 = mergeSortByAmount(list2);
        return mergeByAmount(list1, list2);
    }

//  --------------- sort by category ----------------------------------------------

    private ArrayList<Transaction> mergeByCategory(ArrayList<Transaction> list1, ArrayList<Transaction> list2) {
        ArrayList<Transaction> result = new ArrayList<Transaction>();
        while (list1.size() > 0 && list2.size() > 0) {
            if (list1.get(0).compareCategories(list2.get(0)) > 0) {
                result.add(list2.get(0));
                list2.remove(0);
            } else {
                result.add(list1.get(0));
                list1.remove(0);
            }
        }
        // at this point list1 or list2 is empty
        while (list1.size() > 0) {
            result.add(list1.get(0));
            list1.remove(0);
        }
        while (list2.size() > 0) {
            result.add(list2.get(0));
            list2.remove(0);
        }
        return result;
    }

    private ArrayList<Transaction> mergeSortByCategory(ArrayList<Transaction> list) {
        ArrayList<Transaction> list1 = new ArrayList<Transaction>();
        ArrayList<Transaction> list2 = new ArrayList<Transaction>();
        int n = list.size();
        if (n == 0)
            return null;
        if (n == 1)
            return list;
        n = n / 2;
        for (int i = 0; i < n; i++) {
            list1.add(list.get(i));
        }
        for (int i = n; i < list.size(); i++) {
            list2.add(list.get(i));
        }
        list1 = mergeSortByCategory(list1);
        list2 = mergeSortByCategory(list2);
        return mergeByCategory(list1, list2);
    }

//  -------------------------------------------------------------------------------

    /**
     * This method creates a copy of transactionList, sorted by a certain criteria/
     * parameter/ field of Transaction. This happens without changing anything
     * internally in the transactionList itself. The result is always sorted in an
     * ASCENDING fashion.
     *
     * @param comparator - the number of parameter/ field of Transaction to sort by
     * @return ListIterator of the sorted list (ListIterator can be traversed in
     *         EITHER direction)
     */
    public ListIterator<Transaction> sort(int comparator) {
        ArrayList<Transaction> resultList = new ArrayList<Transaction>();
        switch (comparator) {
            case Transaction.REF_NUMBER:
                resultList = mergeSortByRef(transactionList);
                break;
            case Transaction.DESCRIPTION:
                resultList = mergeSortByDescription(transactionList);
                break;
            case Transaction.MEMO:
                resultList = mergeSortByMemo(transactionList);
                break;
            case Transaction.AMOUNT:
                resultList = mergeSortByAmount(transactionList);
                break;
            case Transaction.CATEGORY:
                resultList = mergeSortByCategory(transactionList);
                break;
            default:
                resultList = mergeSortByDate(transactionList);
        }
        return resultList.listIterator();
    }

//	public static void main(String[] args) {
//
//		Transaction t;
//		Calendar c;  // import java.util.Calendar;
//		TransactionList l = new TransactionList();
//		ListIterator<Transaction> i;
//
//		c = Transaction.returnCalendarFromOFX("20220415120000");
//		t = new Transaction(c, "ABCDEF123456XYZ", "CUB Foods 1379", "Big Shopping Today", -241.15,
//				Transaction.ESSENTIALS);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220417120000");
//		t = new Transaction(c, "F1234BF1556XYZA", "CUB Foods 1378", "Big Shopping Again!", -211.54,
//				Transaction.ESSENTIALS);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220420120000");
//		t = new Transaction(c, "CDEF123456XYZAB", "XFinity Monthly", "Acct 23514470A9    ", -73.00,
//				Transaction.ENTERTAINMENT);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220428120000");
//		t = new Transaction(c, "OBCDEF123456XYZ", "BP Petrol", "12.56gal.         ", -44.89, Transaction.TRANSPORT);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220501120000");
//		t = new Transaction(c, "AB12CDEF123456X", "CUB Foods 1378", "Small bag of Stuff", -22.38,
//				Transaction.ESSENTIALS);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220525120000");
//		t = new Transaction(c, "KJVDFNSSE5A6XYZ", "ONLINE PAYMENT", "THANK YOU        ", 400.00, Transaction.INCOME);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220525120000");
//		t = new Transaction(c, "ABCDDEF12456XYZ", "Netflix.com", "                ", -21.04, Transaction.ENTERTAINMENT);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220525120000");
//		t = new Transaction(c, "NLDFSV123456XYZ", "HOTELSCOM654684", "HOTELS.COM      ", -112.00, Transaction.OTHER);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220602120000");
//		t = new Transaction(c, "NLAWECDEF123456", "QUIZNOS #2304", "                 ", -17.40,
//				Transaction.ENTERTAINMENT);
//		l.add(t);
//		c = Transaction.returnCalendarFromOFX("20220607120000");
//		t = new Transaction(c, "YIQJQ123456AXYZ", "JCPENNEY 0496", "Pair of jeans   ", -45.00, Transaction.ESSENTIALS);
//		l.add(t);
//
//		System.out.println("\n------------------------------ Sort by DATE ---------------------------------------");
//		i = l.sort(Transaction.POSTED_DATE);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//
//		System.out.println("\n------------------------------ Sort by REFERENCE -----------------------------------");
//		i = l.sort(Transaction.REF_NUMBER);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//
//		System.out.println("\n------------------------------ Sort by DESCRIPTION --------------------------------");
//		i = l.sort(Transaction.DESCRIPTION);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//
//		System.out.println("\n------------------------------ Sort by MEMO ---------------------------------------");
//		i = l.sort(Transaction.MEMO);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//
//		System.out.println("\n------------------------------ Sort by AMOUNT -------------------------------------");
//		i = l.sort(Transaction.AMOUNT);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//
//		System.out.println("\n------------------------------ Sort by CATEGORY -----------------------------------");
//		i = l.sort(Transaction.CATEGORY);
//		while (i.hasNext()) {
//			System.out.println(i.next());
//		}
//		System.out.println("\n");
//		while (i.hasPrevious()) {
//			System.out.println(i.previous());
//		}
//	}
}