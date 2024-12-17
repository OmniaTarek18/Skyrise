export const deleteAccountAPI = async (id) => {
  console.log(id);
  const url = `http://localhost:8080/delete?accountId=${id}`; // Use path parameter for id

  try {
    const response = await fetch(url, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    return true;
  } catch (error) {
    console.error(error.message);
  }
  return false;
};
