package domein;

import java.util.ArrayList;

import exceptions.GeenJuistePlaatsException;
import exceptions.PlaatIsBezetException;

public class Koninkrijk {
	private static final int rij = 5;
	private static final int kolom = 5;
	private Tegel[][] bord;

	/**
	 * Een array voor de grid aanmaken
	 */
	public Koninkrijk() {
		bord = new Tegel[rij][kolom];
	}

	/**
	 * Zet kasteel in de grid
	 * 
	 * @param rij
	 * @param kolom
	 * @param kasteel
	 */
	public void setKasteel(int rij, int kolom, KasteelTegel kasteel) {
		bord[rij][kolom] = kasteel;
	}

	/**
	 * Get het bord
	 * 
	 * @return het bord
	 */
	public Tegel[][] getBord() {
		return bord;
	}

	/**
	 * Dominotegel op de grid plaatsen volgens x-y coördinaten
	 * 
	 * @param domino
	 * @param x
	 * @param y
	 */
	public void plaatsDomino(DominoTegel domino, int x, int y) throws Exception {
		if (x < 0 || y < 0 || (x == rij - 1 && !domino.isHorizontaal()) || (y == kolom - 1 && domino.isHorizontaal())) {
			throw new IndexOutOfBoundsException();

		} else if (!isPlaatsVrij(x, y)) {
			throw new PlaatIsBezetException();

		} else if (isPlaatsVrij(x, y) && !isPlaatsVrij(x + 1, y) && !isPlaatsVrij(x - 1, y) && !isPlaatsVrij(x, y + 1)
				&& !isPlaatsVrij(x, y - 1)) {
			throw new PlaatIsBezetException();

		} else if (isKasteelHier(x, y) || (isKasteelHier(x, y + 1) && domino.isHorizontaal())
				|| (isKasteelHier(x + 1, y) && !domino.isHorizontaal())) {
			throw new PlaatIsBezetException();

			// Kijken op kasteel en horizontaal
		} else if (domino.isHorizontaal()
				&& (isKasteelHier(x + 1, y) || isKasteelHier(x - 1, y) || isKasteelHier(x, y - 1)
						|| isKasteelHier(x - 1, y + 1) || isKasteelHier(x + 1, y + 1) || isKasteelHier(x, y + 2))) {
			// ......./......./.......
			// ......./Kasteel/X / X
			// ......./......./.......
			if (isKasteelHier(x, y - 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}

				// ......./......./.......
				// X / X /Kasteel/.......
				// ......./......./.......
			} else if (isKasteelHier(x, y + 2) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}

				// .X....../.X....../.......
				// ......./Kasteel/.......
				// ......./......./.......
			} else if (isKasteelHier(x + 1, y + 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}
				// ......./.X...../.X.....
				// ......./Kasteel/.......
				// ......./......./.......
			} else if (isKasteelHier(x + 1, y) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}

				// ......./......./.......
				// ......./Kasteel/.......
				// ......./.X.... /.X.....
			} else if (isKasteelHier(x - 1, y) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}

				// ......./......./.......
				// ......./Kasteel/.......
				// .X...../.X...../.......
			} else if (isKasteelHier(x - 1, y + 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x, y + 1)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}
			}
			// Kijken op kasteel en verticaal
		} else if (!domino.isHorizontaal() && (isKasteelHier(x, y + 1) || isKasteelHier(x + 1, y - 1) 
				|| isKasteelHier(x + 2, y) || isKasteelHier(x, y - 1) || isKasteelHier(x - 1, y) || isKasteelHier(x + 1, y + 1))) {
			/*
			 *  
			 *  
			 * 
			 * 
			 */
			// ......./......./.x.....
			// ......./Kasteel/.X.....
			// ......./......./.......
			if (isKasteelHier(x + 1, y - 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

				// ......./......./.......
				// ......./Kasteel/.X.....
				// ......./......./.X.....
			} else if (isKasteelHier(x, y - 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

				// ......./......./.......
				// .X...../Kasteel/.......
				// .X...../......./.......
			} else if (isKasteelHier(x, y + 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

				// .X...../......./.......
				// .X...../Kasteel/.......
				// ......./......./.......
			} else if (isKasteelHier(x + 1, y + 1) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

				// ......./.X...../.......
				// ......./Kasteel/.......
				// ......./......./.......
			} else if (isKasteelHier(x + 2, y) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}
				
				// ......./......./.......
				// ......./Kasteel/.......
				// ......./.X...../.......
			} else if (isKasteelHier(x - 1, y) && isPlaatsVrij(x, y) && isPlaatsVrij(x + 1, y)) {
				if (!domino.isSpiegeld()) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				} else {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

			}

			// Kijken of er een zelfde tegel naast ligt horizonaal
			// .../.?./.?./..
			// .?./.X./.X./.?.
			// .../.?./.?./...
		} else if (domino.isHorizontaal()) {
			if (!domino.isSpiegeld() && (isZelfdeTegel(domino.getTegels()[0], bord[x][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.max(x - 1, 0)][y])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.min(x + 1, rij - 1)][y])
					|| isZelfdeTegel(domino.getTegels()[1], bord[x][Math.min(y + 2, kolom - 1)])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.max(x - 1, 0)][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[1],
							bord[Math.min(x + 1, rij - 1)][Math.min(y + 1, kolom - 1)]))) {

				if (isPlaatsVrij(x, y + 1)) {
					bord[x][y] = domino.getTegels()[0];
					bord[x][y + 1] = domino.getTegels()[1];
				}

			} else if (domino.isSpiegeld() && (isZelfdeTegel(domino.getTegels()[1], bord[x][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.max(x - 1, 0)][y])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.min(x + 1, rij - 1)][y])
					|| isZelfdeTegel(domino.getTegels()[0], bord[x][Math.min(y + 2, kolom - 1)])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.max(x - 1, 0)][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[0],
							bord[Math.min(x + 1, rij - 1)][Math.min(y + 1, kolom - 1)]))) {
				if (isPlaatsVrij(x, y + 1)) {
					bord[x][y] = domino.getTegels()[1];
					bord[x][y + 1] = domino.getTegels()[0];
				}

			} else {
				throw new GeenJuistePlaatsException();
			}

			// .../.?./...
			// .?./.X./.?.
			// .?./.X./.?.
			// .../.?./...

		} else if (!domino.isHorizontaal()) {
			if (!domino.isSpiegeld() && (isZelfdeTegel(domino.getTegels()[0], bord[x][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.max(x - 1, 0)][y])
					|| isZelfdeTegel(domino.getTegels()[0], bord[x][Math.min(y + 1, kolom - 1)])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.min(x + 2, rij - 1)][y])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.min(x + 1, rij - 1)][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[1],
							bord[Math.min(x + 1, rij - 1)][Math.min(y + 1, kolom - 1)]))) {

				if (isPlaatsVrij(x + 1, y)) {
					bord[x][y] = domino.getTegels()[0];
					bord[x + 1][y] = domino.getTegels()[1];
				}

			} else if (domino.isSpiegeld() && (isZelfdeTegel(domino.getTegels()[1], bord[x][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[1], bord[Math.max(x - 1, 0)][y])
					|| isZelfdeTegel(domino.getTegels()[1], bord[x][Math.min(y + 1, kolom - 1)])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.min(x + 2, rij - 1)][y])
					|| isZelfdeTegel(domino.getTegels()[0], bord[Math.min(x + 1, rij - 1)][Math.max(y - 1, 0)])
					|| isZelfdeTegel(domino.getTegels()[0],
							bord[Math.min(x + 1, rij - 1)][Math.min(y + 1, kolom - 1)]))) {

				if (isPlaatsVrij(x + 1, y)) {
					bord[x][y] = domino.getTegels()[1];
					bord[x + 1][y] = domino.getTegels()[0];
				}

			} else {
				throw new GeenJuistePlaatsException();
			}

		} else {
			throw new GeenJuistePlaatsException();
		}

		System.out.println();
		for (Tegel[] tegels : bord) {
			for (Tegel tegel : tegels) {
				if (tegel == null)
					System.out.printf("%7s", "x");
				else
					System.out.printf("%7s", tegel.getLandschap());
			}
			System.out.println();
		}
	}

	/**
	 * Checkt of de plaats vrij is aan de hand van x-y coördinaten
	 * 
	 * @param x
	 * @param y
	 * @return true/false
	 */
	public boolean isPlaatsVrij(int x, int y) {
		int coordinaatX = x;
		int coordinaatY = y;

		if (coordinaatX < 0)
			coordinaatX = 0;
		else if (coordinaatY < 0)
			coordinaatY = 0;
		else if (coordinaatX >= rij)
			coordinaatX = rij - 1;
		else if (coordinaatY >= kolom)
			coordinaatY = kolom - 1;

		return bord[coordinaatX][coordinaatY] == null;
	}

	/**
	 * Checkt of kasteel op het geselecteerde coordinaat staat
	 * 
	 * @param x
	 * @param y
	 * @return true/false
	 */
	public boolean isKasteelHier(int x, int y) {
		return bord[Math.min(Math.max(x, 0), rij - 1)][Math.min(Math.max(y, 0), kolom - 1)] instanceof KasteelTegel;
	}

	/**
	 * Kijk na of tegel1 hetzelfde landschap heeft als tegel2 Gebruikt voor het
	 * plaatsen van een domino naast een andere domino
	 * 
	 * @param tegel1
	 * @param tegel2
	 * @return true/false
	 */
	public boolean isZelfdeTegel(Tegel tegel1, Tegel tegel2) {
		if (tegel1 != null && tegel2 != null)
			return tegel1.getLandschap().equals(tegel2.getLandschap());

		return false;
	}

	/**
	 * Checkt of de plaats al dan niet bezit is
	 * 
	 * @param rij
	 * @param kolom
	 * @return
	 */
	public boolean isBezet(int rij, int kolom) {
		return bord[rij][kolom] == null;
	}

	/**
	 * Kijkt na of het bord volzet is
	 * 
	 * @return false als het bord niet volzet is
	 */
	public boolean isBordVolzet() {
		for (int i = 0; i < rij; i++) {
			for (int j = 0; j < kolom; j++) {
				if (!isBezet(i, j))
					return false;
			}
		}

		return true;
	}

	/**
	 * Berekent de oppervlakte van het landschap die de speler bezit
	 * 
	 * @param landschap
	 * @return de oppervlakte
	 */
	public ArrayList<ArrayList<Integer>> berekenOppervlakte(String landschap) {
		ArrayList<ArrayList<Integer>> oppervlakte = new ArrayList<>();

		int[][] passeerdeTegel = new int[rij][kolom];
		int[][] passeerdeTegelVoorKroon = new int[rij][kolom];

		for (int i = 0; i < passeerdeTegel.length; i++) {
			for (int j = 0; j < passeerdeTegel.length; j++) {
				if (bord[i][j] != null && bord[i][j].getLandschap().equals(landschap)) {
					passeerdeTegel[i][j] = 1;
					passeerdeTegelVoorKroon[i][j] = 1;
				} else {
					passeerdeTegel[i][j] = 0;
					passeerdeTegelVoorKroon[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < bord.length; i++) {
			for (int j = 0; j < bord.length; j++) {
				ArrayList<Integer> tijdelijkeArray = new ArrayList<>();

				int grootte = berekenHoeveelheidTegels(passeerdeTegel, i, j);
				int kroontjes = berekenKroontjes(passeerdeTegelVoorKroon, bord, i, j, 0);

				if (grootte > 1 || grootte == 1 && kroontjes >= 1) {
					tijdelijkeArray.add(grootte);
					tijdelijkeArray.add(kroontjes);

				} else if (tijdelijkeArray.size() > 0) {
					oppervlakte.add(tijdelijkeArray);
				}
			}
		}

		return oppervlakte;
	}

	/**
	 * Berekent hoeveelheid tegels
	 * 
	 * @param grid
	 * @param i
	 * @param j
	 * @return het aantal tegels
	 */
	private int berekenHoeveelheidTegels(int[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid.length)
			throw new IllegalArgumentException();
		// Nakijken of input klopt

		if (grid[i][j] == 0)
			return 0;

		grid[i][j] = 0;
		int teller = 1;
		teller += berekenHoeveelheidTegels(grid, i + 1, j);
		teller += berekenHoeveelheidTegels(grid, i - 1, j);
		teller += berekenHoeveelheidTegels(grid, i, j + 1);
		teller += berekenHoeveelheidTegels(grid, i, j - 1);

		return teller;
	}

	/**
	 * Bereken het aantal kroontjes door het bord af te gaan
	 * 
	 * @param grid      de 5x5 van een specifiek landschap gerepresenteerd door een
	 *                  1
	 * @param bord      de hele 5x5 van het spel
	 * @param i         de x coördinaten van het bord
	 * @param j         de y coördinaten van het bord
	 * @param kroontjes
	 * @return het aantal kroontjes
	 */
	public int berekenKroontjes(int[][] grid, Tegel[][] bord, int i, int j, int kroontjes) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid.length)
			throw new IllegalArgumentException();
		// Nakijken of input klopt

		if (grid[i][j] == 0)
			return 0;

		grid[i][j] = 0;
		kroontjes = bord[i][j].getKroontjes();
		kroontjes += berekenKroontjes(grid, bord, i + 1, j, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i - 1, j, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i, j + 1, bord[i][j].getKroontjes());
		kroontjes += berekenKroontjes(grid, bord, i, j - 1, bord[i][j].getKroontjes());

		return kroontjes;
	}

}