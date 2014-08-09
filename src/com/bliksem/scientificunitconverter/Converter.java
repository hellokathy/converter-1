package com.bliksem.scientificunitconverter;

import static javax.measure.unit.SI.BECQUEREL;
import static javax.measure.unit.SI.BIT;
import static javax.measure.unit.SI.CENTI;
import static javax.measure.unit.SI.COULOMB;
import static javax.measure.unit.SI.CUBIC_METRE;
import static javax.measure.unit.SI.GRAM;
import static javax.measure.unit.SI.GRAY;
import static javax.measure.unit.SI.JOULE;
import static javax.measure.unit.SI.KELVIN;
import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.LUX;
import static javax.measure.unit.SI.METRE;
import static javax.measure.unit.SI.METRES_PER_SECOND;
import static javax.measure.unit.SI.METRES_PER_SQUARE_SECOND;
import static javax.measure.unit.SI.MOLE;
import static javax.measure.unit.SI.NEWTON;
import static javax.measure.unit.SI.PASCAL;
import static javax.measure.unit.SI.RADIAN;
import static javax.measure.unit.SI.SECOND;
import static javax.measure.unit.SI.SIEVERT;
import static javax.measure.unit.SI.SQUARE_METRE;
import static javax.measure.unit.SI.STERADIAN;
import static javax.measure.unit.SI.TESLA;
import static javax.measure.unit.SI.WATT;
import static javax.measure.unit.SI.WEBER;

import java.util.HashMap;
import java.util.TreeMap;

import javax.measure.converter.LogConverter;
import javax.measure.converter.RationalConverter;
import javax.measure.quantity.Acceleration;
import javax.measure.quantity.AmountOfSubstance;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.DataAmount;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.quantity.DynamicViscosity;
import javax.measure.quantity.ElectricCharge;
import javax.measure.quantity.ElectricCurrent;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Force;
import javax.measure.quantity.Illuminance;
import javax.measure.quantity.KinematicViscosity;
import javax.measure.quantity.Length;
import javax.measure.quantity.MagneticFlux;
import javax.measure.quantity.MagneticFluxDensity;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.RadiationDoseAbsorbed;
import javax.measure.quantity.RadiationDoseEffective;
import javax.measure.quantity.RadioactiveActivity;
import javax.measure.quantity.SolidAngle;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.ProductUnit;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import android.util.Log;

public class Converter
{
	private Converter()
	{
	}

	public static Converter getInstance()
	{
		return INSTANCE;
	}

	private static final Converter INSTANCE = new Converter();

	// ///////////
	// SI Units //
	// ///////////

	public static final Unit<Length> METER = METRE;
	public static final Unit<Mass> GRAM = KILOGRAM.divide(1000);
	public static final Unit<Temperature> CELSIUS = KELVIN.plus(273.15);
	public static final Unit<Velocity> METRES_PER_SECOND = new ProductUnit<Velocity>(METRE.divide(SECOND));
	public static final Unit<Velocity> METERS_PER_SECOND = METRES_PER_SECOND;
	public static final Unit<Acceleration> METRES_PER_SQUARE_SECOND = new ProductUnit<Acceleration>(METRES_PER_SECOND.divide(SECOND));
	public static final Unit<Acceleration> METERS_PER_SQUARE_SECOND = METRES_PER_SQUARE_SECOND;
	public static final Unit<Area> SQUARE_METRE = new ProductUnit<Area>(METRE.times(METRE));
	public static final Unit<Volume> CUBIC_METRE = new ProductUnit<Volume>(SQUARE_METRE.times(METRE));
	public static final Unit<Length> KILOMETRE = METER.times(1000);
	public static final Unit<Length> KILOMETER = KILOMETRE;
	public static final Unit<Length> CENTIMETRE = METRE.divide(100);
	public static final Unit<Length> CENTIMETER = CENTIMETRE;
	public static final Unit<Length> MILLIMETRE = METRE.divide(1000);
	public static final Unit<Length> MILLIMETER = MILLIMETRE;
	public static final Unit<Velocity> METRE_PER_SECOND = METRES_PER_SECOND;
	public static final Unit<Acceleration> METRE_PER_SQUARE_SECOND = METRES_PER_SQUARE_SECOND;

	// //////////////////
	// NonSI Constants //
	// //////////////////

	private static final int STANDARD_GRAVITY_DIVIDEND = 980665;
	private static final int STANDARD_GRAVITY_DIVISOR = 100000;
	private static final int INTERNATIONAL_FOOT_DIVIDEND = 3048;
	private static final int INTERNATIONAL_FOOT_DIViSOR = 10000;
	private static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;
	private static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;
	private static final double AVOGADRO_CONSTANT = 6.02214199e23; // (1/mol).
	private static final double ELEMENTARY_CHARGE = 1.602176462e-19; // (C).

	// //////////////
	// NonSI Units //
	// //////////////

	public static final Unit<Dimensionless> PERCENT = Unit.ONE.divide(100);
	public static final Unit<Dimensionless> DECIBEL = Unit.ONE.transform(new LogConverter(10).inverse().concatenate(new RationalConverter(1, 10)));
	public static final Unit<AmountOfSubstance> ATOM = MOLE.divide(AVOGADRO_CONSTANT);
	public static final Unit<Length> FOOT = (METRE.times(INTERNATIONAL_FOOT_DIVIDEND).divide(INTERNATIONAL_FOOT_DIViSOR));
	public static final Unit<Length> FOOT_SURVEY_US = (METRE.times(1200).divide(3937));
	public static final Unit<Length> YARD = (FOOT.times(3));
	public static final Unit<Length> INCH = (FOOT.divide(12));
	public static final Unit<Length> MILE = (METRE.times(1609344).divide(1000));
	public static final Unit<Length> NAUTICAL_MILE = (METRE.times(1852));
	public static final Unit<Length> ANGSTROM = (METRE.divide(10000000000L));
	public static final Unit<Length> LIGHT_YEAR = (METRE.times(9.460528405e15));
	public static final Unit<Length> PARSEC = (METRE.times(30856770e9));
	public static final Unit<Length> POINT = (INCH.times(13837).divide(1000000));
	public static final Unit<Length> PIXEL = (INCH.divide(72));
	public static final Unit<Length> COMPUTER_POINT = PIXEL;
	public static final Unit<Duration> MINUTE = (SI.SECOND.times(60));
	public static final Unit<Duration> HOUR = (MINUTE.times(60));
	public static final Unit<Duration> DAY = (HOUR.times(24));
	public static final Unit<Duration> WEEK = (DAY.times(7));
	public static final Unit<Duration> YEAR = (SECOND.times(31556952));
	public static final Unit<Duration> MONTH = (YEAR.divide(12));
	public static final Unit<Duration> DAY_SIDEREAL = (SECOND.times(86164.09));
	public static final Unit<Duration> YEAR_CALENDAR = (DAY.times(365));
	public static final Unit<Mass> POUND = (KILOGRAM.times(AVOIRDUPOIS_POUND_DIVIDEND).divide(AVOIRDUPOIS_POUND_DIVISOR));
	public static final Unit<Mass> OUNCE = (POUND.divide(16));
	public static final Unit<Mass> TON_US = (POUND.times(2000));
	public static final Unit<Mass> TON_UK = (POUND.times(2240));
	public static final Unit<Mass> METRIC_TON = (KILOGRAM.times(1000));
	public static final Unit<Temperature> RANKINE = (KELVIN.times(5).divide(9));
	public static final Unit<Temperature> FAHRENHEIT = (RANKINE.plus(459.67));
	public static final Unit<Angle> REVOLUTION = (RADIAN.times(2.0 * Math.PI));
	public static final Unit<Angle> DEGREE_ANGLE = (REVOLUTION.divide(360));
	public static final Unit<Angle> MINUTE_ANGLE = (DEGREE_ANGLE.divide(60));
	public static final Unit<Angle> SECOND_ANGLE = (MINUTE_ANGLE.divide(60));
	public static final Unit<Angle> CENTIRADIAN = (RADIAN.divide(100));
	public static final Unit<Angle> GRADE = (REVOLUTION.divide(400));
	public static final Unit<Velocity> KILOMETRES_PER_HOUR = SI.KILOMETRE.divide(NonSI.HOUR).asType(Velocity.class);
	public static final Unit<Velocity> MACH = (METRES_PER_SECOND.times(331.6));
	public static final Unit<Velocity> C = (METRES_PER_SECOND.times(299792458));
	public static final Unit<Area> ARE = (SQUARE_METRE.times(100));
	public static final Unit<Area> HECTARE = (ARE.times(100)); // Exact.
	public static final Unit<DataAmount> BYTE = (BIT.times(8));
	public static final Unit<DataAmount> OCTET = BYTE;
	public static final Unit<Energy> ERG = (JOULE.divide(10000000));
	public static final Unit<Illuminance> LAMBERT = (LUX.times(10000));
	public static final Unit<MagneticFlux> MAXWELL = (WEBER.divide(100000000));
	public static final Unit<MagneticFluxDensity> GAUSS = (TESLA.divide(10000));
	public static final Unit<Force> DYNE = (NEWTON.divide(100000));
	public static final Unit<Power> HORSEPOWER = (WATT.times(735.499));
	public static final Unit<Pressure> ATMOSPHERE = (PASCAL.times(101325));
	public static final Unit<Pressure> BAR = (PASCAL.times(100000));
	public static final Unit<Pressure> INCH_OF_MERCURY = (PASCAL.times(3386.388));
	public static final Unit<RadiationDoseAbsorbed> RAD = (GRAY.divide(100));
	public static final Unit<RadiationDoseEffective> REM = (SIEVERT.divide(100));
	public static final Unit<Volume> LITRE = (CUBIC_METRE.divide(1000));
	public static final Unit<Volume> LITER = LITRE;
	public static final Unit<Volume> CUBIC_INCH = (INCH.pow(3).asType(Volume.class));
	public static final Unit<Volume> GALLON_LIQUID_US = (CUBIC_INCH.times(231));
	public static final Unit<Volume> GALLON_DRY_US = (CUBIC_INCH.times(2688025).divide(10000));
	public static final Unit<Volume> GALLON_UK = (LITRE.times(454609).divide(100000));
	public static final Unit<Volume> OUNCE_LIQUID_UK = (GALLON_UK.divide(160));
	public static final Unit<?> ROENTGEN = (COULOMB.divide(KILOGRAM).times(2.58e-4));
	public static final Unit<Velocity> MILES_PER_HOUR = (NonSI.MILE.divide(NonSI.HOUR)).asType(Velocity.class);
	public static final Unit<Velocity> KNOT = (NonSI.NAUTICAL_MILE.divide(NonSI.HOUR)).asType(Velocity.class);
	public static final Unit<Acceleration> G = (METRES_PER_SQUARE_SECOND.times(STANDARD_GRAVITY_DIVIDEND).divide(STANDARD_GRAVITY_DIVISOR));
	public static final Unit<Force> KILOGRAM_FORCE = (NEWTON.times(STANDARD_GRAVITY_DIVIDEND).divide(STANDARD_GRAVITY_DIVISOR));
	public static final Unit<Force> POUND_FORCE = (NEWTON.times(1L * AVOIRDUPOIS_POUND_DIVIDEND * STANDARD_GRAVITY_DIVIDEND).divide(1L * AVOIRDUPOIS_POUND_DIVISOR * STANDARD_GRAVITY_DIVISOR));
	public static final Unit<ElectricCurrent> GILBERT = (SI.AMPERE.times(10.0 / (4.0 * Math.PI)));
	public static final Unit<Energy> ELECTRON_VOLT = (JOULE.times(ELEMENTARY_CHARGE));
	public static final Unit<Pressure> MILLIMETER_OF_MERCURY = (PASCAL.times(133.322));
	public static final Unit<SolidAngle> SPHERE = (STERADIAN.times(4.0 * Math.PI));
	public static final Unit<RadioactiveActivity> CURIE = (BECQUEREL.times(37000000000L));
	public static final Unit<RadioactiveActivity> RUTHERFORD = (SI.BECQUEREL.times(1000000));
	public static final Unit<Volume> OUNCE_LIQUID_US = (GALLON_LIQUID_US.divide(128));
	@SuppressWarnings("unchecked")
	public static final Unit<DynamicViscosity> POISE = ((Unit<DynamicViscosity>) GRAM.divide(CENTI(METRE).times(SECOND)));
	@SuppressWarnings("unchecked")
	public static final Unit<KinematicViscosity> STOKE = ((Unit<KinematicViscosity>) CENTI(METRE).pow(2).divide(SECOND));
	public static final Unit<Length> ASTRONOMICAL_UNIT = (METRE.times(149597870691.0));
	public static final Unit<Duration> YEAR_SIDEREAL = (SECOND.times(31558149.54));
	public static final Unit<Mass> ATOMIC_MASS = (KILOGRAM.times(1e-3 / AVOGADRO_CONSTANT));
	public static final Unit<Mass> ELECTRON_MASS = (KILOGRAM.times(9.10938188e-31));
	public static final Unit<ElectricCharge> E = (COULOMB.times(ELEMENTARY_CHARGE));
	public static final Unit<ElectricCharge> FARADAY = (COULOMB.times(ELEMENTARY_CHARGE * AVOGADRO_CONSTANT)); // e/mol
	public static final Unit<ElectricCharge> FRANKLIN = (COULOMB.times(3.3356e-10));

	HashMap<String, Unit<?>> units;

	// TreeMap<String, Unit<?>> unitObjects = new TreeMap<String, Unit<?>>();

	public HashMap<String, Unit<?>> getAllUnits(TreeMap<String, String> unitConversionUnit, TreeMap<String, Double> unitTimes)
	{
		// 1. add SI & NonSI Unit definitions

		this.initUnitObjects();

		// 2. add custom units

		for (String key : unitConversionUnit.keySet())
		{
			Unit<?> newUnit = units.get(unitConversionUnit.get(key)).times(unitTimes.get(key));
			units.put(key, newUnit);
		}

		return units;
	}

	private void initUnitObjects()
	{
		units = new HashMap<String, Unit<?>>();

		// SI //
		
		units.put("METER", METER);
		units.put("GRAM", GRAM);
		units.put("CELSIUS", CELSIUS);
		units.put("METRES_PER_SECOND", METRES_PER_SECOND);
		units.put("METERS_PER_SECOND", METERS_PER_SECOND);
		units.put("METRES_PER_SQUARE_SECOND", METRES_PER_SQUARE_SECOND);
		units.put("METERS_PER_SQUARE_SECOND", METERS_PER_SQUARE_SECOND);
		units.put("SQUARE_METRE", SQUARE_METRE);
		units.put("CUBIC_METRE", CUBIC_METRE);
		units.put("KILOMETRE", KILOMETRE);
		units.put("KILOMETER", KILOMETER);
		units.put("CENTIMETRE", CENTIMETRE);
		units.put("CENTIMETER", CENTIMETER);
		units.put("MILLIMETRE", MILLIMETRE);
		units.put("MILLIMETER", MILLIMETER);
		units.put("METRE_PER_SECOND", METRE_PER_SECOND);
		units.put("METRE_PER_SQUARE_SECOND", METRE_PER_SQUARE_SECOND);

		// NonSI //
		
		units.put("PERCENT", PERCENT);
		units.put("DECIBEL", DECIBEL);
		units.put("ATOM", ATOM);
		units.put("FOOT", FOOT);
		units.put("FOOT_SURVEY_US", FOOT_SURVEY_US);
		units.put("YARD", YARD);
		units.put("INCH", INCH);
		units.put("MILE", MILE);
		units.put("NAUTICAL_MILE", NAUTICAL_MILE);
		units.put("ANGSTROM", ANGSTROM);
		units.put("LIGHT_YEAR", LIGHT_YEAR);
		units.put("PARSEC", PARSEC);
		units.put("POINT", POINT);
		units.put("PIXEL", PIXEL);
		units.put("COMPUTER_POINT", COMPUTER_POINT);
		units.put("MINUTE", MINUTE);
		units.put("HOUR", HOUR);
		units.put("DAY", DAY);
		units.put("WEEK", WEEK);
		units.put("YEAR", YEAR);
		units.put("MONTH", MONTH);
		units.put("DAY_SIDEREAL", DAY_SIDEREAL);
		units.put("YEAR_CALENDAR", YEAR_CALENDAR);
		units.put("POUND", POUND);
		units.put("OUNCE", OUNCE);
		units.put("TON_US", TON_US);
		units.put("TON_UK", TON_UK);
		units.put("METRIC_TON", METRIC_TON);
		units.put("RANKINE", RANKINE);
		units.put("FAHRENHEIT", FAHRENHEIT);
		units.put("REVOLUTION", REVOLUTION);
		units.put("DEGREE_ANGLE", DEGREE_ANGLE);
		units.put("MINUTE_ANGLE", MINUTE_ANGLE);
		units.put("SECOND_ANGLE", SECOND_ANGLE);
		units.put("CENTIRADIAN", CENTIRADIAN);
		units.put("GRADE", GRADE);
		units.put("KILOMETRES_PER_HOUR", KILOMETRES_PER_HOUR);
		units.put("MACH", MACH);
		units.put("C", C);
		units.put("ARE", ARE);
		units.put("HECTARE", HECTARE);
		units.put("BYTE", BYTE);
		units.put("OCTET", OCTET);
		units.put("ERG", ERG);
		units.put("LAMBERT", LAMBERT);
		units.put("MAXWELL", MAXWELL);
		units.put("GAUSS", GAUSS);
		units.put("DYNE", DYNE);
		units.put("HORSEPOWER", HORSEPOWER);
		units.put("ATMOSPHERE", ATMOSPHERE);
		units.put("BAR", BAR);
		units.put("INCH_OF_MERCURY", INCH_OF_MERCURY);
		units.put("RAD", RAD);
		units.put("REM", REM);
		units.put("LITRE", LITRE);
		units.put("LITER", LITER);
		units.put("CUBIC_INCH", CUBIC_INCH);
		units.put("GALLON_LIQUID_US", GALLON_LIQUID_US);
		units.put("GALLON_DRY_US", GALLON_DRY_US);
		units.put("GALLON_UK", GALLON_UK);
		units.put("OUNCE_LIQUID_UK", OUNCE_LIQUID_UK);
		units.put("ROENTGEN", ROENTGEN);
		units.put("MILES_PER_HOUR", MILES_PER_HOUR);
		units.put("KNOT", KNOT);
		units.put("G", G);
		units.put("KILOGRAM_FORCE", KILOGRAM_FORCE);
		units.put("POUND_FORCE", POUND_FORCE);
		units.put("GILBERT", GILBERT);
		units.put("ELECTRON_VOLT", ELECTRON_VOLT);
		units.put("MILLIMETER_OF_MERCURY", MILLIMETER_OF_MERCURY);
		units.put("SPHERE", SPHERE);
		units.put("CURIE", CURIE);
		units.put("RUTHERFORD", RUTHERFORD);
		units.put("OUNCE_LIQUID_US", OUNCE_LIQUID_US);
		units.put("POISE", POISE);
		units.put("STOKE", STOKE);
		units.put("ASTRONOMICAL_UNIT", ASTRONOMICAL_UNIT);
		units.put("YEAR_SIDEREAL", YEAR_SIDEREAL);
		units.put("ATOMIC_MASS", ATOMIC_MASS);
		units.put("ELECTRON_MASS", ELECTRON_MASS);
		units.put("E", E);
		units.put("FARADAY", FARADAY);
		units.put("FRANKLIN", FRANKLIN);
	}

}
