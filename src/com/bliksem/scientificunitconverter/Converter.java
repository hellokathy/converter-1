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
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

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

	// ////////////
	// SI Units //
	// ////////////

	Unit<Acceleration> METERS_PER_SECOND_SQUARED = SI.METERS_PER_SQUARE_SECOND;

	HashMap<String, Unit<?>> nonSIMap;
	HashMap<String, Unit<?>> sIMap;

	TreeMap<String, Unit<?>> unitObjects = new TreeMap<String, Unit<?>>();

	public TreeMap<String, Unit<?>> getUnitObjects(TreeMap<String, String> unitConversionUnit, TreeMap<String, Double> unitTimes)
	{
		return unitObjects;
	}

	public void initUnitMaps()
	{
        nonSIMap = new HashMap<String, Unit<?>>();
        sIMap = new HashMap<String, Unit<?>>();
        
        nonSIMap.put("PERCENT", PERCENT);
        nonSIMap.put("DECIBEL", DECIBEL);
        nonSIMap.put("ATOM", ATOM);
        nonSIMap.put("FOOT", FOOT);
        nonSIMap.put("FOOT_SURVEY_US", FOOT_SURVEY_US);
        nonSIMap.put("YARD", YARD);
        nonSIMap.put("INCH", INCH);
        nonSIMap.put("MILE", MILE);
        nonSIMap.put("NAUTICAL_MILE", NAUTICAL_MILE);
        nonSIMap.put("ANGSTROM", ANGSTROM);
        nonSIMap.put("LIGHT_YEAR", LIGHT_YEAR);
        nonSIMap.put("PARSEC", PARSEC);
        nonSIMap.put("POINT", POINT);
        nonSIMap.put("PIXEL", PIXEL);
        nonSIMap.put("COMPUTER_POINT", COMPUTER_POINT);
        nonSIMap.put("MINUTE", MINUTE);
        nonSIMap.put("HOUR", HOUR);
        nonSIMap.put("DAY", DAY);
        nonSIMap.put("WEEK", WEEK);
        nonSIMap.put("YEAR", YEAR);
        nonSIMap.put("MONTH", MONTH);
        nonSIMap.put("DAY_SIDEREAL", DAY_SIDEREAL);
        nonSIMap.put("YEAR_CALENDAR", YEAR_CALENDAR);
        nonSIMap.put("POUND", POUND);
        nonSIMap.put("OUNCE", OUNCE);
        nonSIMap.put("TON_US", TON_US);
        nonSIMap.put("TON_UK", TON_UK);
        nonSIMap.put("METRIC_TON", METRIC_TON);
        nonSIMap.put("RANKINE", RANKINE);
        nonSIMap.put("FAHRENHEIT", FAHRENHEIT);
        nonSIMap.put("REVOLUTION", REVOLUTION);
        nonSIMap.put("DEGREE_ANGLE", DEGREE_ANGLE);
        nonSIMap.put("MINUTE_ANGLE", MINUTE_ANGLE);
        nonSIMap.put("SECOND_ANGLE", SECOND_ANGLE);
        nonSIMap.put("CENTIRADIAN", CENTIRADIAN);
        nonSIMap.put("GRADE", GRADE);
        nonSIMap.put("KILOMETRES_PER_HOUR", KILOMETRES_PER_HOUR);
        nonSIMap.put("MACH", MACH);
        nonSIMap.put("C", C);
        nonSIMap.put("ARE", ARE);
        nonSIMap.put("HECTARE", HECTARE);
        nonSIMap.put("BYTE", BYTE);
        nonSIMap.put("OCTET", OCTET);
        nonSIMap.put("ERG", ERG);
        nonSIMap.put("LAMBERT", LAMBERT);
        nonSIMap.put("MAXWELL", MAXWELL);
        nonSIMap.put("GAUSS", GAUSS);
        nonSIMap.put("DYNE", DYNE);
        nonSIMap.put("HORSEPOWER", HORSEPOWER);
        nonSIMap.put("ATMOSPHERE", ATMOSPHERE);
        nonSIMap.put("BAR", BAR);
        nonSIMap.put("INCH_OF_MERCURY", INCH_OF_MERCURY);
        nonSIMap.put("RAD", RAD);
        nonSIMap.put("REM", REM);
        nonSIMap.put("LITRE", LITRE);
        nonSIMap.put("LITER", LITER);
        nonSIMap.put("CUBIC_INCH", CUBIC_INCH);
        nonSIMap.put("GALLON_LIQUID_US", GALLON_LIQUID_US);
        nonSIMap.put("GALLON_DRY_US", GALLON_DRY_US);
        nonSIMap.put("GALLON_UK", GALLON_UK);
        nonSIMap.put("OUNCE_LIQUID_UK", OUNCE_LIQUID_UK);
        nonSIMap.put("ROENTGEN", ROENTGEN);
        nonSIMap.put("MILES_PER_HOUR", MILES_PER_HOUR);
        nonSIMap.put("KNOT", KNOT);
        nonSIMap.put("G", G);
        nonSIMap.put("KILOGRAM_FORCE", KILOGRAM_FORCE);
        nonSIMap.put("POUND_FORCE", POUND_FORCE);
        nonSIMap.put("GILBERT", GILBERT);
        nonSIMap.put("ELECTRON_VOLT", ELECTRON_VOLT);
        nonSIMap.put("MILLIMETER_OF_MERCURY", MILLIMETER_OF_MERCURY);
        nonSIMap.put("SPHERE", SPHERE);
        nonSIMap.put("CURIE", CURIE);
        nonSIMap.put("RUTHERFORD", RUTHERFORD);
        nonSIMap.put("OUNCE_LIQUID_US", OUNCE_LIQUID_US);
        nonSIMap.put("POISE", POISE);
        nonSIMap.put("STOKE", STOKE);
        nonSIMap.put("ASTRONOMICAL_UNIT", ASTRONOMICAL_UNIT);
        nonSIMap.put("YEAR_SIDEREAL", YEAR_SIDEREAL);
        nonSIMap.put("ATOMIC_MASS", ATOMIC_MASS);
        nonSIMap.put("ELECTRON_MASS", ELECTRON_MASS);
        nonSIMap.put("E", E);
        nonSIMap.put("FARADAY", FARADAY);
        nonSIMap.put("FRANKLIN", FRANKLIN);   
	}
	
}
