package com.bliksem.scientificunitconverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser
{

	private CSVParser()
	{
	}

	public static CSVParser getInstance()
	{
		return INSTANCE;
	}

	private static final CSVParser INSTANCE = new CSVParser();

	// /////////////////////
	// Converter TreeMaps //
	// /////////////////////

	private TreeMap<String, String> unitSymbols = new TreeMap<String, String>();
	private TreeMap<String, String> unitNiceNames = new TreeMap<String, String>();
	private TreeMap<String, String> unitConversionUnit = new TreeMap<String, String>();
	private TreeMap<String, Double> unitTimes = new TreeMap<String, Double>();

	public TreeMap<String, String> getUnitSymbols()
	{
		return unitSymbols;
	}

	public TreeMap<String, String> getUnitConversionUnit()
	{
		return unitConversionUnit;
	}

	public TreeMap<String, Double> getUnitTimes()
	{
		return unitTimes;
	}

	public TreeMap<String, String> getUnitNiceNames()
	{
		return unitNiceNames;
	}
	
	public void parse_csv(InputStream is)
	{

		// populates unitSymbols, unitConversionUnit, unitNiceNames & unitTimes

		CSVReader reader = null;
		reader = new CSVReader(new InputStreamReader(is));

		String[] nextLine;
		try
		{
			while ((nextLine = reader.readNext()) != null)
			{
				if (nextLine[0].equals("name") && nextLine[1].equals("symbol"))
				{
					continue;
				}

				unitSymbols.put(nextLine[0], nextLine[1]);
				unitNiceNames.put(nextLine[0], nextLine[2]);
				unitConversionUnit.put(nextLine[0], nextLine[3]);
				unitTimes.put(nextLine[0], Double.parseDouble(nextLine[4]));
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
