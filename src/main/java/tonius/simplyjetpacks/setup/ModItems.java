package tonius.simplyjetpacks.setup;

import tonius.simplyjetpacks.Log;
import tonius.simplyjetpacks.SimplyJetpacks;
import tonius.simplyjetpacks.config.Config;
import tonius.simplyjetpacks.crafting.UpgradingRecipe;
import tonius.simplyjetpacks.integration.*;
import tonius.simplyjetpacks.item.ItemPack.ItemFluxPack;
import tonius.simplyjetpacks.item.rewrite.*;
import tonius.simplyjetpacks.util.ItemHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public abstract class ModItems {
	public static ItemJetpack itemJetpack;

	public static ItemFluxpack itemFluxPack;

	public static ItemMeta metaItem;
	public static ItemMetaMods metaItemMods;

	public static ItemJetpack jetpacksEIO;
	public static ItemFluxPack fluxPacksEIO;
	public static ItemMeta armorPlatings;

	public static ItemStack jetpackCreative;
	public static ItemStack fluxPackCreative;

	public static ItemStack particleDefault;
	public static ItemStack particleSmoke;
	public static ItemStack particleNone;
	public static ItemStack particleRainbowSmoke;

	public static ItemStack leatherStrap;
	public static ItemStack unitFlightControlEmpty;
	public static ItemStack reinforcedGliderWings;
	public static ItemStack unitFlightControl;

	public static ItemStack ingotDarkSoularium;

	public static ItemStack thrusterEIO1;
	public static ItemStack thrusterEIO2;
	public static ItemStack thrusterEIO3;
	public static ItemStack thrusterEIO4;
	public static ItemStack thrusterEIO5;

	public static ItemStack thrusterVanilla1;
	public static ItemStack thrusterVanilla2;
	public static ItemStack thrusterVanilla3;

	//EnderIO Packs
	public static ItemStack armorPlatingEIO1;
	public static ItemStack armorPlatingEIO2;
	public static ItemStack armorPlatingEIO3;
	public static ItemStack armorPlatingEIO4;

	public static ItemStack jetpackEIO1;
	public static ItemStack jetpackEIO1Armored;
	public static ItemStack jetpackEIO2;
	public static ItemStack jetpackEIO2Armored;
	public static ItemStack jetpackEIO3;
	public static ItemStack jetpackEIO3Armored;
	public static ItemStack jetpackEIO4;
	public static ItemStack jetpackEIO4Armored;
	public static ItemStack jetpackEIO5;

	public static ItemStack fluxPackEIO1;
	public static ItemStack fluxPackEIO2;
	public static ItemStack fluxPackEIO2Armored;
	public static ItemStack fluxPackEIO3;
	public static ItemStack fluxPackEIO3Armored;
	public static ItemStack fluxPackEIO4;
	public static ItemStack fluxPackEIO4Armored;

	//ThermalExpansion Packs
	public static ItemStack thrusterTE1;
	public static ItemStack thrusterTE2;
	public static ItemStack thrusterTE3;
	public static ItemStack thrusterTE4;
	public static ItemStack thrusterTE5;

	public static ItemStack jetpackTE1;
	public static ItemStack jetpackTE2;
	public static ItemStack jetpackTE3;
	public static ItemStack jetpackTE4;
	public static ItemStack jetpackTE5;

	public static ItemStack jetpackVanilla1;
	public static ItemStack jetpackVanilla2;
	public static ItemStack jetpackVanilla3;

	public static ItemStack enderiumUpgrade;

	public static boolean integrateEIO = ModType.ENDER_IO.loaded && Config.enableIntegrationEIO;
	public static boolean integrateTE = ModType.THERMAL_EXPANSION.loaded && Config.enableIntegrationTE;
	public static boolean integrateTD = ModType.THERMAL_DYNAMICS.loaded && Config.enableIntegrationTD;
	public static boolean integrateVanilla = Config.enableIntegrationVanilla;

	public static void preInit() {
		registerItems();
		registerOreDicts();

	}

	public static void init() {
		if (integrateEIO) {
			EIOItems.init();
		}
		if (integrateTE) {
			TEItems.init();
			if (integrateTD) {
				TDItems.init();
			}
		}

		registerRecipes();
		doIMC();
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemJetpack) {
			((ItemJetpack) item).registerItemModel();
		}

		if (item instanceof ItemFluxpack) {
			((ItemFluxpack) item).registerItemModel();
		}

		if (item instanceof ItemMeta) {
			((ItemMeta) item).registerItemModel();
		}

		if (item instanceof ItemMetaMods) {
			((ItemMetaMods) item).registerItemModel();
		}

		return item;
	}

	private static void registerOreDicts() {
		for (MetaItems item : MetaItems.PARTICLE_CUSTOMIZERS) {
			OreDictionary.registerOre("particleCustomizer", new ItemStack(metaItem, 1, item.ordinal()));

		}
		if (integrateEIO) {
			OreDictionary.registerOre(MetaItemsMods.INGOT_DARK_SOULARIUM.getName(), ingotDarkSoularium);
		}
	}

	private static void registerItems() {
		Log.info("Registering items...");

		//Jetpacks
		itemJetpack = register(new ItemJetpack("itemJetpack"));

		//Meta Items
		metaItem = register(new ItemMeta("metaItem"));
		metaItemMods = register(new ItemMetaMods("metaItemMods"));

		//FluxPacks
		itemFluxPack = register(new ItemFluxpack("itemFluxpack"));

		jetpackCreative = Jetpack.CREATIVE_JETPACK.getStackJetpack();
		fluxPackCreative = Fluxpack.CREATIVE_FLUXPACK.getStackFluxpack();

		particleDefault = MetaItems.PARTICLE_DEFAULT.getStackMetaItem();
		particleSmoke = MetaItems.PARTICLE_SMOKE.getStackMetaItem();
		particleNone = MetaItems.PARTICLE_NONE.getStackMetaItem();
		particleRainbowSmoke = MetaItems.PARTICLE_RAINBOWSMOKE.getStackMetaItem();

		leatherStrap = MetaItems.LEATHER_STRAP.getStackMetaItem();


		if (integrateEIO) {
			ingotDarkSoularium = MetaItemsMods.INGOT_DARK_SOULARIUM.getStackMetaItemEIO();
			unitFlightControlEmpty = MetaItemsMods.UNIT_FLIGHT_CONTROL_EMPTY.getStackMetaItemEIO();
			reinforcedGliderWings = MetaItemsMods.REINFORCED_GLIDERWINGS.getStackMetaItemEIO();
			unitFlightControl = MetaItemsMods.UNIT_FLIGHT_CONTROL.getStackMetaItemEIO();

			thrusterEIO1 = MetaItemsMods.THRUSTER_EIO_1.getStackMetaItemEIO();
			thrusterEIO2 = MetaItemsMods.THRUSTER_EIO_2.getStackMetaItemEIO();
			thrusterEIO3 = MetaItemsMods.THRUSTER_EIO_3.getStackMetaItemEIO();
			thrusterEIO4 = MetaItemsMods.THRUSTER_EIO_4.getStackMetaItemEIO();
			thrusterEIO5 = MetaItemsMods.THRUSTER_EIO_5.getStackMetaItemEIO();

			armorPlatingEIO1 = MetaItemsMods.ARMOR_PLATING_EIO_1.getStackMetaItemEIO();
			armorPlatingEIO2 = MetaItemsMods.ARMOR_PLATING_EIO_2.getStackMetaItemEIO();
			armorPlatingEIO3 = MetaItemsMods.ARMOR_PLATING_EIO_3.getStackMetaItemEIO();
			armorPlatingEIO4 = MetaItemsMods.ARMOR_PLATING_EIO_4.getStackMetaItemEIO();

			jetpackEIO1 = Jetpack.JETPACK_EIO_1.getStackJetpack();
			jetpackEIO2 = Jetpack.JETPACK_EIO_2.getStackJetpack();
			jetpackEIO3 = Jetpack.JETPACK_EIO_3.getStackJetpack();
			jetpackEIO4 = Jetpack.JETPACK_EIO_4.getStackJetpack();
			jetpackEIO5 = Jetpack.JETPLATE_EIO_5.getStackJetpack();
			jetpackEIO1Armored = Jetpack.JETPACK_EIO_1_ARMORED.getStackJetpack();
			jetpackEIO2Armored = Jetpack.JETPACK_EIO_2_ARMORED.getStackJetpack();
			jetpackEIO3Armored = Jetpack.JETPACK_EIO_3_ARMORED.getStackJetpack();
			jetpackEIO4Armored = Jetpack.JETPACK_EIO_4_ARMORED.getStackJetpack();

			fluxPackEIO1 = Fluxpack.FLUXPACK_EIO1.getStackFluxpack();
			fluxPackEIO2 = Fluxpack.FLUXPACK_EIO2.getStackFluxpack();
			fluxPackEIO3 = Fluxpack.FLUXPACK_EIO3.getStackFluxpack();
			fluxPackEIO2Armored = Fluxpack.FLUXPACK_EIO2_ARMORED.getStackFluxpack();
			fluxPackEIO3Armored = Fluxpack.FLUXPACK_EIO3_ARMORED.getStackFluxpack();
			//fluxPackEIO4 = Fluxpack.FLUXPACK_EIO4.getStackFluxpack();
		}

		if (integrateTE) {
			thrusterTE1 = MetaItemsMods.THRUSTER_TE_1.getStackMetaItemEIO();
			thrusterTE2 = MetaItemsMods.THRUSTER_TE_2.getStackMetaItemEIO();
			thrusterTE3 = MetaItemsMods.THRUSTER_TE_3.getStackMetaItemEIO();
			thrusterTE4 = MetaItemsMods.THRUSTER_TE_4.getStackMetaItemEIO();
			thrusterTE5 = MetaItemsMods.THRUSTER_TE_5.getStackMetaItemEIO();

			jetpackTE1= Jetpack.JETPACK_TE_1.getStackJetpack();
			jetpackTE2 = Jetpack.JETPACK_TE_2.getStackJetpack();
			jetpackTE3 = Jetpack.JETPACK_TE_3.getStackJetpack();
			jetpackTE4 = Jetpack.JETPACK_TE_4.getStackJetpack();
			jetpackTE5 = Jetpack.JETPLATE_TE_5.getStackJetpack();
		}

		if (integrateVanilla) {
			jetpackVanilla1 = Jetpack.JETPACK_VANILLA_1.getStackJetpack();
			jetpackVanilla2 = Jetpack.JETPACK_VANILLA_2.getStackJetpack();
			jetpackVanilla3 = Jetpack.JETPACK_VANILLA_3.getStackJetpack();

			thrusterVanilla1 = MetaItems.THRUSTER_VANILLA_1.getStackMetaItem();
			thrusterVanilla2 = MetaItems.THRUSTER_VANILLA_2.getStackMetaItem();
			thrusterVanilla3 = MetaItems.THRUSTER_VANILLA_3.getStackMetaItem();
		}
	}

	private static void registerRecipes() {

		ItemHelper.addShapedOreRecipe(Jetpack.POTATO_JETPACK.getStackJetpack(), "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POTATO, 'R', "dustRedstone");
		ItemHelper.addShapedOreRecipe(Jetpack.POTATO_JETPACK.getStackJetpack(), "S S", "NPN", "R R", 'S', Items.STRING, 'N', "nuggetGold", 'P', Items.POISONOUS_POTATO, 'R', "dustRedstone");

		ItemHelper.addShapedOreRecipe(leatherStrap, "LIL", "LIL", 'L', Items.LEATHER, 'I', "ingotIron");

		GameRegistry.addRecipe(new UpgradingRecipe(jetpackCreative, "J", "P", 'J', jetpackCreative, 'P', "particleCustomizer"));

		Object dustCoal = OreDictionary.getOres("dustCoal").size() > 0 ? "dustCoal" : new ItemStack(Items.COAL);
		ItemHelper.addShapedOreRecipe(particleDefault, " D ", "DCD", " D ", 'C', dustCoal, 'D', Blocks.TORCH);
		ItemHelper.addShapedOreRecipe(particleNone, " D ", "DCD", " D ", 'C', dustCoal, 'D', "blockGlass");
		ItemHelper.addShapedOreRecipe(particleSmoke, " C ", "CCC", " C ", 'C', dustCoal);
		ItemHelper.addShapedOreRecipe(particleRainbowSmoke, " R ", " C ", "G B", 'C', dustCoal, 'R', "dyeRed", 'G', "dyeLime", 'B', "dyeBlue");

		if (integrateEIO) {
			ItemHelper.addShapedOreRecipe(thrusterEIO1, "ICI", "PCP", "DSD", 'I', "ingotConductiveIron", 'P', EIOItems.redstoneConduit, 'C', EIOItems.basicCapacitor, 'D', EIOItems.basicGear, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO2, "ICI", "PCP", "DSD", 'I', "ingotElectricalSteel", 'P', EIOItems.energyConduit1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.machineChassis, 'S', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterEIO3, "ICI", "PCP", "DSD", 'I', "ingotEnergeticAlloy", 'P', EIOItems.energyConduit2, 'C', EIOItems.doubleCapacitor, 'D', EIOItems.pulsatingCrystal, 'S', "ingotRedstoneAlloy");
			ItemHelper.addShapedOreRecipe(thrusterEIO4, "ICI", "PCP", "DSD", 'I', "ingotVibrantAlloy", 'P', EIOItems.energyConduit3, 'C', EIOItems.octadicCapacitor, 'D', EIOItems.vibrantCrystal, 'S', "ingotRedstoneAlloy");
			ItemHelper.addShapedOreRecipe(thrusterEIO5, "SES", "CTC", "   ", 'T', thrusterEIO4, 'S', "ingotDarkSoularium", 'E', unitFlightControl, 'C', EIOItems.octadicCapacitor);

			ItemHelper.addShapedOreRecipe(reinforcedGliderWings, "  S", " SP", "SPP", 'S', "ingotDarkSoularium", 'P', armorPlatingEIO2);
			ItemHelper.addShapedOreRecipe(unitFlightControlEmpty, "FLF", "LHL", "FLF", 'L', "ingotElectricalSteel", 'F', "ingotDarkSoularium", 'H', "blockGlassHardened");

			ItemHelper.addShapedOreRecipe(armorPlatingEIO1, "SIS", "ISI", "SIS", 'I', "ingotIron", 'S', "itemSilicon");

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "IBI", "IJI", "T T", 'I', "ingotConductiveIron", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO1, 'J', leatherStrap));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "IBI", "IJI", "T T", 'I', "ingotElectricalSteel", 'B', EIOItems.basicCapacitor, 'T', thrusterEIO2, 'J', jetpackEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "IBI", "IJI", "T T", 'I', "ingotEnergeticAlloy", 'B', EIOItems.doubleCapacitor, 'T', thrusterEIO3, 'J', jetpackEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "IBI", "IJI", "T T", 'I', "ingotVibrantAlloy", 'B', EIOItems.octadicCapacitor, 'T', thrusterEIO4, 'J', jetpackEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO5, "OAO", "PJP", "TCT", 'A', EIOItems.enderCrystal, 'J', jetpackEIO4Armored, 'O', "ingotDarkSoularium", 'C', fluxPackEIO3Armored, 'T', thrusterEIO5, 'P', reinforcedGliderWings));

			for (Jetpack jetpack : Jetpack.PACKS_EIO) {
				GameRegistry.addRecipe(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1Armored, "P", "J", 'J', jetpackEIO1, 'P', armorPlatingEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO1, "J", 'J', jetpackEIO1Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2Armored, "P", "J", 'J', jetpackEIO2, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO2, "J", 'J', jetpackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3Armored, "P", "J", 'J', jetpackEIO3, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO3, "J", 'J', jetpackEIO3Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4Armored, "P", "J", 'J', jetpackEIO4, 'P', armorPlatingEIO4));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackEIO4, "J", 'J', jetpackEIO4Armored));

			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO1, "CUC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'U', EIOItems.capacitorBankBasic, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "CBC", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotVibrantAlloy", 'P', EIOItems.vibrantCrystal));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
			/*
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "BCB", "ISI", "CPC", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
			*/
		}

		if (integrateTE) {
			Object ductFluxLeadstone = integrateTD ? TDItems.ductFluxLeadstone : "blockGlass";
			Object ductFluxHardened = integrateTD ? TDItems.ductFluxHardened : "blockGlass";
			Object ductFluxRedstoneEnergy = integrateTD ? TDItems.ductFluxRedstoneEnergy : "blockGlassHardened";
			Object ductFluxResonant = integrateTD ? TDItems.ductFluxResonant : "blockGlassHardened";

			ItemHelper.addShapedOreRecipe(thrusterTE1, "ICI", "PDP", "IRI", 'I', "ingotLead", 'P', ductFluxLeadstone, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoSteam, 'R', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterTE2, "ICI", "PDP", "IRI", 'I', "ingotInvar", 'P', ductFluxHardened, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoReactant, 'R', "dustRedstone");
			ItemHelper.addShapedOreRecipe(thrusterTE3, "ICI", "PDP", "IRI", 'I', "ingotElectrum", 'P', ductFluxRedstoneEnergy, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoMagmatic, 'R', TEItems.bucketRedstone);
			ItemHelper.addShapedOreRecipe(thrusterTE4, "ICI", "PDP", "IRI", 'I', "ingotEnderium", 'P', ductFluxResonant, 'C', TEItems.powerCoilGold, 'D', TEItems.dynamoEnervation, 'R', TEItems.bucketRedstone);

			GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE1, "IBI", "IJI", "T T", 'I', "ingotLead", 'B', TEItems.capacitorBasic, 'T', thrusterTE1, 'J', leatherStrap));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE2, "IBI", "IJI", "T T", 'I', "ingotInvar", 'B', TEItems.capacitorHardened, 'T', thrusterTE2, 'J', jetpackTE1));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE3, "IBI", "IJI", "T T", 'I', "ingotElectrum", 'B', TEItems.capacitorReinforced, 'T', thrusterTE3, 'J', jetpackTE2));
			GameRegistry.addRecipe(new UpgradingRecipe(jetpackTE4, "IBI", "IJI", "T T", 'I', "ingotEnderium", 'B', TEItems.capacitorResonant, 'T', thrusterTE4, 'J', jetpackTE3));

			for (Jetpack jetpack : Jetpack.PACKS_TE) {
				GameRegistry.addRecipe(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
			}

			if (integrateVanilla) {
				ItemHelper.addShapedOreRecipe(thrusterVanilla1, " I ", "IFI", "IBI", 'I', Items.IRON_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
				ItemHelper.addShapedOreRecipe(thrusterVanilla2, " I ", "IFI", "IBI", 'I', Items.GOLD_INGOT, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);
				ItemHelper.addShapedOreRecipe(thrusterVanilla3, " I ", "IFI", "IBI", 'I', Items.DIAMOND, 'F', Blocks.FURNACE, 'B', Items.BLAZE_POWDER);

				//Jetpacks
				GameRegistry.addRecipe(new UpgradingRecipe(jetpackVanilla1, "IBI", "IJI", "T T", 'I', Items.IRON_INGOT, 'B', Items.COMPARATOR, 'T', thrusterVanilla1, 'J', leatherStrap));
				GameRegistry.addRecipe(new UpgradingRecipe(jetpackVanilla2, "IBI", "IJI", "T T", 'I', Items.GOLD_INGOT, 'B', Blocks.REDSTONE_BLOCK, 'T', thrusterVanilla2, 'J', jetpackVanilla1));
				GameRegistry.addRecipe(new UpgradingRecipe(jetpackVanilla3, "IBI", "IJI", "T T", 'I', Items.DIAMOND, 'B', Blocks.REDSTONE_BLOCK, 'T', thrusterVanilla3, 'J', jetpackVanilla2));

				for (Jetpack jetpack : Jetpack.PACKS_VANILLA) {
					GameRegistry.addRecipe(new UpgradingRecipe(jetpack.getStackJetpack(1), "J", "P", 'J', jetpack.getStackJetpack(1), 'P', "particleCustomizer"));
				}
			}
		}
	}

//			fluxPackEIO2Armored = fluxPacksEIO.putPack(102, Packs.fluxPackEIO2Armored);
//			fluxPackEIO3Armored = fluxPacksEIO.putPack(103, Packs.fluxPackEIO3Armored);
//			fluxPackEIO4 = fluxPacksEIO.putPack(4, Packs.fluxPackEIO4);
//			fluxPackEIO4Armored = fluxPacksEIO.putPack(104, Packs.fluxPackEIO4Armored);

	/*private static void registerRecipes()
	{
		SimplyJetpacks.logger.info("Registering recipes");

		if(integrateEIO)
		{
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO1, "CIC", "ISI", "IPI", 'S', leatherStrap, 'C', EIOItems.basicCapacitor, 'I', "ingotConductiveIron", 'P', "dustCoal"));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "DCD", "ISI", "IPI", 'S', fluxPackEIO1, 'C', EIOItems.basicCapacitor, 'D', EIOItems.doubleCapacitor, 'I', "ingotElectricalSteel", 'P', "dustGold"));
			if(EIOItems.capacitorBank != null && EIOItems.capacitorBank.getItem() != null)
			{
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBank, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "BCB", "ISI", "CPC", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankVibrant, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
			}
			else
			{
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "CBC", "ISI", "IPI", 'S', fluxPackEIO2, 'C', EIOItems.doubleCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotEnergeticAlloy", 'P', EIOItems.pulsatingCrystal));
				GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "CBC", "ISI", "BPB", 'S', fluxPackEIO3, 'C', EIOItems.octadicCapacitor, 'B', EIOItems.capacitorBankOld, 'I', "ingotPhasedGold", 'P', EIOItems.vibrantCrystal));
			}

			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2Armored, "P", "J", 'J', fluxPackEIO2, 'P', armorPlatingEIO1));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO2, "J", 'J', fluxPackEIO2Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3Armored, "P", "J", 'J', fluxPackEIO3, 'P', armorPlatingEIO2));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO3, "J", 'J', fluxPackEIO3Armored));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4Armored, "P", "J", 'J', fluxPackEIO4, 'P', armorPlatingEIO3));
			GameRegistry.addRecipe(new UpgradingRecipe(fluxPackEIO4, "J", 'J', fluxPackEIO4Armored));
		}
	}*/

	private static void doIMC() {
		SimplyJetpacks.logger.info("Doing intermod communication");

		if (integrateEIO) {
			ItemStack ingotConductiveIron = OreDictionary.getOres("ingotConductiveIron").get(0).copy();
			ingotConductiveIron.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Conductive Iron Armor Plating", 3200, armorPlatingEIO1, ingotConductiveIron, null, armorPlatingEIO2);

			ItemStack ingotElectricalSteel = OreDictionary.getOres("ingotElectricalSteel").get(0).copy();
			ingotElectricalSteel.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Electrical Steel Armor Plating", 4800, armorPlatingEIO2, ingotElectricalSteel, null, armorPlatingEIO3);

			ItemStack ingotDarkSteel = OreDictionary.getOres("ingotDarkSteel").get(0).copy();
			ingotDarkSteel.stackSize = 10;
			EIORecipes.addAlloySmelterRecipe("Dark Steel Armor Plating", 6400, armorPlatingEIO3, ingotDarkSteel, null, armorPlatingEIO4);

			ItemStack ingotSoularium = OreDictionary.getOres("ingotSoularium").get(0).copy();
			ingotDarkSteel.stackSize = 1;
			EIORecipes.addAlloySmelterRecipe("Dark Soularium Alloy", 32000, ingotDarkSteel, ingotSoularium, EIOItems.pulsatingCrystal, ingotDarkSoularium);

			EIORecipes.addSoulBinderRecipe("Flight Control Unit", 75000, 8, "Bat", unitFlightControlEmpty, unitFlightControl);
		}
	}
}
