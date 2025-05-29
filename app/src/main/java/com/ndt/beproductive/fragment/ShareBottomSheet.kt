import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ndt.beproductive.databinding.FragmentShareBottomSheetBinding

class ShareBottomSheet(private val link: String) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShareBottomSheetBinding.inflate(inflater, container, false)

        binding.tvLink.text = link

        binding.btnCopy.setOnClickListener {
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Link", link)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Đã sao chép liên kết", Toast.LENGTH_SHORT).show()
        }

        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, link)
            }
            startActivity(Intent.createChooser(intent, "Chia sẻ liên kết qua"))
        }

        return binding.root
    }
}
