package al132.alchemistry.compat.jei.category;

import al132.alchemistry.Alchemistry;
import al132.alchemistry.Ref;
import al132.alchemistry.compat.jei.JEIIntegration;
import al132.alchemistry.blocks.evaporator.EvaporatorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class EvaporatorRecipeCategory implements IRecipeCategory<EvaporatorRecipe> {
    private IGuiHelper guiHelper;
    private final static int u = 40;
    private final static int v = 11;

    public EvaporatorRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Alchemistry.data.MODID, JEIIntegration.EVAPORATOR_CATEGORY);
    }

    @Override
    public Class<? extends EvaporatorRecipe> getRecipeClass() {
        return EvaporatorRecipe.class;
    }

    @Override
    public String getTitle() {
        return I18n.format("alchemistry.jei.evaporator");
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(new ResourceLocation(Alchemistry.data.MODID, "textures/gui/evaporator_gui.png"),
                u, v, 100, 125);
    }

    @Override
    public IDrawable getIcon() {
        return guiHelper.createDrawableIngredient(new ItemStack(Ref.evaporator));
    }

    @Override
    public void setIngredients(EvaporatorRecipe recipe, IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.input);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.output);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, EvaporatorRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();


        int x = 121 - u;
        int y = 51 - v;
        guiItemStacks.init(0, false, x, y);
        guiItemStacks.set(0, ingredients.getOutputs(VanillaTypes.ITEM).get(0));

        x = 48 - u;
        y = 69 - u;
        FluidStack inputStack = ingredients.getInputs(VanillaTypes.FLUID).get(0).get(0);
        guiFluidStacks.init(1, true, x, y, 16, 60, inputStack.getAmount(), false, null);
        guiFluidStacks.set(1, inputStack);
    }
}
