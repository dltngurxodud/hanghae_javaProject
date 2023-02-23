package report8;

class SutdaDeck1 {
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];

    SutdaDeck1() {
        for (int i = 0; i < cards.length; i++) {
            int num = i % 10 + 1;
            boolean isKwang = (num == 1 || num == 3 || num == 8);
            cards[i] = new SutdaCard(num, isKwang);
        }
    }

    void shuffle1() {
        for (int i = 0; i < cards.length; i++) {
            int randomIndex = (int) (Math.random() * cards.length);
            SutdaCard temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    SutdaCard pick1(int index) {
        return cards[index];
    }

    SutdaCard pick1() {
        int randomIndex = (int) (Math.random() * cards.length);
        return cards[randomIndex];
    }
}

class SutdaCard1 {
    int num;
    boolean isKwang;

    SutdaCard1() {
        this(1, true);
    }

    SutdaCard1(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }

    public String toString() {
        return num + (isKwang ? "K" : "");
    }
}

class Exercise7_2 {
    public static void main(String args[]) {
        SutdaDeck1 deck = new SutdaDeck1();

        System.out.println(deck.pick1(0));
        System.out.println(deck.pick1());
        deck.shuffle1();

        for (int i = 0; i < deck.cards.length; i++)
            System.out.print(deck.cards[i] + ",");

        System.out.println();
        System.out.println(deck.pick1(0));
    }
}