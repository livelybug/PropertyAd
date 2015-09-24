<%@ tag language="java" pageEncoding="ISO-8859-1"%>

	<tr>
		<td>name</td>
		<td>
			<input type="text" name="name" size="30">
		</td>
	</tr>

	<tr>
		<td>address</td>
		<td>
			<input type="text" name="address" size="30">
		</td>
	</tr>

	<tr>
		<td>rent_sale</td>
		<td>
			<input type="radio" name="rent_sale" value="rent">rent
			<input type="radio" name="rent_sale" value="sale">sale
		</td>
	</tr>


	<tr>
		<td>image</td>
		<td>
			<input type="file" name="image" size="30">
		</td>
	</tr>

	<tr>
		<td>comment</td>
		<td>
			<input type="text" name="comment" size="30">
		</td>
	</tr>

	<tr>
		<td colspan="1">
			<input type="submit" name="btSubmit" value="Submit">
			<input type="reset" name="btCancel" value="Cancel">
		</td>
	</tr>

	
</table>
</form>
